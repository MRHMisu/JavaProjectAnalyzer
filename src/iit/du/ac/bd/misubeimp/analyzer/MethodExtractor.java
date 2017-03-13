package iit.du.ac.bd.misubeimp.analyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import iit.du.ac.bd.misubeimp.model.Method;

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
					String Modifier = n.getModifiers().toString();
					String ReturnType = n.getType().toString();
					String Name = n.getNameAsString();
					String Signature = n.getDeclarationAsString().toString();

					methods.add(new Method(Modifier, ReturnType, Name, Signature, SourceFileId, ProjectID));
					super.visit(n, arg);
				}
			}.visit(JavaParser.parse(filePath), null);
			System.out.println();
		} catch (IOException e) {
			new RuntimeException(e);
		}

		return methods;
	}

	/*
	 * public static void main(String[] args) { File projectDir = new
	 * File("C://Users//MisuBeImp//Desktop//SimpleCalculatorOperation.java");
	 * List<Method> methods = getAllMethods(projectDir); int m = 0; }
	 */

}
