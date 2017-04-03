package iit.du.ac.bd.misubeimp.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class VaraibleListExample {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		FileInputStream in = new FileInputStream(
				"D:\\NK\\src\\NormalCalculator.java");
		// parse it
		CompilationUnit cu = JavaParser.parse(in);
		AllVisitors allVisitor = new AllVisitors();
		
		allVisitor.visit(cu, null);
		
	}
	private static class AllVisitors extends VoidVisitorAdapter<Void> {
		
		
		@Override
		public void visit(ClassOrInterfaceDeclaration n, Void arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
			System.out.println(n.getNameAsString());
		}

		@Override
		public void visit(MethodDeclaration n, Void arg) {
			// TODO Auto-generated method stub
			//super.visit(n, arg);
			System.out.println(n.getNameAsString());
		}
		
		@Override
		public void visit(VariableDeclarator n, Void arg) {
			// TODO Auto-generated method stub
			//super.visit(n, arg);
			System.out.println(n.toString());
			System.out.println(n.getType());
		}

		
	    
		
	}
}
