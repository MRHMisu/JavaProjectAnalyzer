package misu.be.imp.bd.ac.du.iit;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodPrinter {

	public static void main(String[] args) throws Exception {
		// creates an input stream for the file to be parsed
		FileInputStream in = new FileInputStream("C://Users//MisuBeImp//Desktop//SimpleCalculatorOperation.java");

		// parse it
		CompilationUnit cu = JavaParser.parse(in);

		// visit and print the methods names
		MethodVisitor methodVisitor=new MethodVisitor();
		methodVisitor.visit(cu, null);
		List<Method> methods=methodVisitor.getMethodList();
		MongoDBAccess.getAccess(methods);
		
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
			String Modifier = n.getModifiers().toString();
			String ReturnType = n.getType().toString();
			String Name = n.getNameAsString();
			String Signature = n.getDeclarationAsString().toString();
			Method method = new Method(Modifier, ReturnType, Name, Signature);
			methods.add(method);

		}

		public List<Method> getMethodList() {
			return methods;
		}
	}
}