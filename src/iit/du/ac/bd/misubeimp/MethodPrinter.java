package iit.du.ac.bd.misubeimp;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import iit.du.ac.bd.misubeimp.dao.MongoDBAccess;
import iit.du.ac.bd.misubeimp.model.Method;

public class MethodPrinter {

	public static void main(String[] args) throws Exception {
		// creates an input stream for the file to be parsed
		FileInputStream in = new FileInputStream("D:\\Masters Lab\\ParsingJavaProject\\src\\parser\\FileParser.java");

		// parse it
		CompilationUnit cu = JavaParser.parse(in);
		//cu.get
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
			//super.visit(n, arg);
			String Modifier = n.getModifiers().toString();
			String ReturnType = n.getType().toString();
			String Name = n.getNameAsString();
			String Signature = n.getDeclarationAsString().toString();
			//Method method = new Method(Modifier, ReturnType, Name, Signature);
			//System.out.println(n.);
			// methods.add(method);

		}

		public List<Method> getMethodList() {
			return methods;
		}
	}
}