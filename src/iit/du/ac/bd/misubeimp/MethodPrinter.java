package iit.du.ac.bd.misubeimp;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.github.javaparser.JavaParser;
import com.github.javaparser.Range;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import iit.du.ac.bd.misubeimp.model.Method;

public class MethodPrinter {

	public static void main(String[] args) throws Exception {
		// creates an input stream for the file to be parsed
		FileInputStream in = new FileInputStream(
				"D:\\NK\\src\\NormalCalculator.java");

		// parse it
		CompilationUnit cu = JavaParser.parse(in);
		// cu.get
		// visit and print the methods names
		MethodVisitor methodVisitor = new MethodVisitor();
		methodVisitor.visit(cu, null);
		// List<Method> methods=methodVisitor.getMethodList();
		// MongoDBAccess.getAccess(methods);

	}

	/**
	 * Simple visitor implementation for visiting MethodDeclaration nodes.
	 */
	private static class MethodVisitor extends VoidVisitorAdapter<Void> {

		List<Method> methods = new ArrayList<Method>();

		@Override
		public void visit(MethodDeclaration n, Void arg) {
			/*
			 * here you can access the attributes of the method. this method
			 * will be called for all methods in this CompilationUnit, including
			 * inner class methods
			 */
			// super.visit(n, arg);
			
			Set<Modifier> Modifier = n.getModifiers();
			String ReturnType = n.getType().toString();
			String Name = n.getNameAsString();
			String Signature = n.getDeclarationAsString().toString();
			NodeList<Parameter> parameters = n.getParameters();
			System.out.println(n.getName());
			Range r=n.getRange().get();
			int startLine=r.begin.line;
			System.out.println(startLine);
			int endLine=r.end.line;
			System.out.println(endLine);
			int length=(endLine-startLine)+1;
			System.out.println(length);
			
			
			
			for(Parameter m:parameters)
			{
				//System.out.println(m.getName());
				//System.out.println(m.getType());
				
			}
			//int k = 0;
			//System.out.println(k);
			
			// Method method = new Method(Modifier, ReturnType, Name,
			// Signature);
			// System.out.println(n.);
			// methods.add(method);
			

			Node ll=n.getParentNode().get();
			if (ll instanceof ClassOrInterfaceDeclaration) {
				ClassOrInterfaceDeclaration classofMethods = (ClassOrInterfaceDeclaration) ll;
				//if(classofMethods.isInterface())
				System.out.println(classofMethods.getName());
			}
			if(ll instanceof MethodDeclaration)
			{
				MethodDeclaration methodDeclaration=(MethodDeclaration) ll;
						System.out.println(methodDeclaration.getName());
			}

		}

		public List<Method> getMethodList() {
			return methods;
		}
	}
}