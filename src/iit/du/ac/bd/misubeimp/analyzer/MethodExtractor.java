package iit.du.ac.bd.misubeimp.analyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.bson.types.ObjectId;

import com.github.javaparser.JavaParser;
import com.github.javaparser.Range;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import iit.du.ac.bd.misubeimp.model.Method;
import iit.du.ac.bd.misubeimp.model.MethodBody;
import iit.du.ac.bd.misubeimp.model.MethodParameter;

public class MethodExtractor {

	private ObjectId sourceFileId;
	private ObjectId projectId;
	private String sourcePath;

	public MethodExtractor(ObjectId sourceFileId, ObjectId projectId, String sourcePath) {
		this.sourceFileId = sourceFileId;
		this.projectId = projectId;
		this.sourcePath = sourcePath;
	}

	public List<Method> getAllMethods(File filePath) {
		List<Method> methods = new ArrayList<Method>();
		try {
			new VoidVisitorAdapter<Object>() {
				@Override
				public void visit(MethodDeclaration n, Object arg) {
					Set<Modifier> modifierList = n.getModifiers();
					String returnType = n.getType().toString();
					String name = n.getNameAsString();
					String signature = n.getDeclarationAsString().toString();
					NodeList<Parameter> parametersList = n.getParameters();
					Set<MethodParameter> parameters = new HashSet<MethodParameter>();
					int order = 0;
					for (Parameter m : parametersList) {
						parameters.add(new MethodParameter(order, m.getNameAsString(), m.getType().toString()));
						order++;
					}

					Set<String> modifiers = new TreeSet<String>();
					for (Modifier m : modifierList) {
						modifiers.add(m.toString());
					}

					Range r = n.getRange().get();
					int startLine = r.begin.line;
					int endLine = r.end.line;
					String body = n.getBody().toString();
					MethodBody methodBody = new MethodBody(startLine, endLine, body);

					methods.add(new Method(projectId, sourceFileId, (sourcePath + "." + name), modifiers, returnType,
							name, signature, parameters, methodBody));

					super.visit(n, arg);
				}
			}.visit(JavaParser.parse(filePath), null);
			System.out.println();
		} catch (IOException e) {
			new RuntimeException(e);
		}

		return methods;
	}

}
