package iit.du.ac.bd.misubeimp.analyzer;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
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

	private ObjectId SourceFileId;
	private ObjectId ProjectID;

	public MethodExtractor(ObjectId sourceFileId, ObjectId projectID) {
		this.SourceFileId = sourceFileId;
		this.ProjectID = projectID;
	}

	public Set<Method> getAllMethods(File filePath) {
		Set<Method> methods = new HashSet<Method>();
		try {
			new VoidVisitorAdapter<Object>() {
				@Override
				public void visit(MethodDeclaration n, Object arg) {
					Set<Modifier> modifiers = n.getModifiers();
					String returnType = n.getType().toString();
					String name = n.getNameAsString();
					String signature = n.getDeclarationAsString().toString();
					NodeList<Parameter> parametersList = n.getParameters();
					Set<MethodParameter> parameters=new HashSet<MethodParameter>();
					int order=0;
					for(Parameter m:parametersList)
					{
						parameters.add(new MethodParameter(order,m.getNameAsString(),m.getType().toString()));
						order++;
					}
					
					Range r=n.getRange().get();
					int startLine=r.begin.line;
					int endLine=r.end.line;
					int length=(endLine-startLine)+1;
					String body=n.getBody().toString();
					//MethodBody methodBody=new MethodBody(startLine, endLine, length, body);
					
					
					//methods.add(new Method(Modifier, ReturnType, Name, Signature,parameterModel,SourceFileId, ProjectID));
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
