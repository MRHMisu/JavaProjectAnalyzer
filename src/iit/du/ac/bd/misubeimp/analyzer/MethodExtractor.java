package iit.du.ac.bd.misubeimp.analyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import iit.du.ac.bd.misubeimp.model.Method;

public class MethodExtractor {

	public List<Method> getAllMethods(File filePath) {
		List<Method> methods = new ArrayList<Method>();
		try {
			new VoidVisitorAdapter<Object>() {
				@Override
				public void visit(MethodDeclaration n, Object arg) {
					//super.visit(n, arg);
					String Modifier = n.getModifiers().toString();
					String ReturnType = n.getType().toString();
					String Name = n.getNameAsString();
					String Signature = n.getDeclarationAsString().toString();
					methods.add(new Method(Modifier, ReturnType, Name, Signature));
					super.visit(n, arg);
				}
			}.visit(JavaParser.parse(filePath), null);
			System.out.println();
		} catch (IOException e) {
			new RuntimeException(e);
		}

		return methods;
	}
	
	/**
	 * Simple visitor implementation for visiting MethodDeclaration nodes.
	 */
	private static class MethodVisitor extends VoidVisitorAdapter<Void> {
		int count=0;
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
			
			methods.add(new Method(Modifier, ReturnType, Name, Signature));
			count++;
			System.out.print(count+",");
		}

		public List<Method> getMethodList() {
			return methods;
		}
	}
	
	public  List<Method> getTheMethods(File filePath) throws Exception {
		// creates an input stream for the file to be parsed
		FileInputStream in = new FileInputStream(filePath);

		// parse it
		CompilationUnit cu = JavaParser.parse(in);
		//cu.get
		// visit and print the methods names
		MethodVisitor methodVisitor = new MethodVisitor();
		methodVisitor.visit(cu, null);
		List<Method> methods=methodVisitor.getMethodList();
		System.out.println();
		return methods;

	}
	/*
	 * public static void main(String[] args) { File projectDir = new
	 * File("C://Users//MisuBeImp//Desktop//SimpleCalculatorOperation.java");
	 * List<Method> methods = getAllMethods(projectDir); int m = 0; }
	 */

}
