package iit.du.ac.bd.misubeimp.example;

import java.io.File;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.typesystem.Type;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.google.common.base.Strings;

import iit.du.ac.bd.misubeimp.support.DirectoryExplorer;

public class MethodCallsExample {
	
    public static void listMethodCalls(File projectDir) {
    	
    	CombinedTypeSolver combinedTypeSolver= new CombinedTypeSolver();
    	combinedTypeSolver.add(new ReflectionTypeSolver());
        combinedTypeSolver.add(new JavaParserTypeSolver(new File("D:\\My All Lab Work\\JAVA LAB\\Design Pattern\\Design Pattern Template Method\\src")));
       
    	
        new DirectoryExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
            System.out.println(path);
            System.out.println(Strings.repeat("=", path.length()));
            try {
                new VoidVisitorAdapter<Object>() {
                    @Override
                    public void visit(MethodCallExpr n, Object arg) {
                        super.visit(n, arg);
                       
                        System.out.println(" [L " + n.getRange() + "] " + n);
                        
                    }
                }.visit(JavaParser.parse(file), null);
                System.out.println(); // empty line
            } catch (IOException e) {
                new RuntimeException(e);
            }
        }).explore(projectDir);
    }

    public static void main(String[] args) {
        File projectDir = new File("D:\\My All Lab Work\\JAVA LAB\\Design Pattern\\Design Pattern Template Method");
        listMethodCalls(projectDir);
        int m=0;
    }
}
