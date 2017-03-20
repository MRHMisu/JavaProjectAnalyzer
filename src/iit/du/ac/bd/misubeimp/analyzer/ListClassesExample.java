package iit.du.ac.bd.misubeimp.analyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import iit.du.ac.bd.misubeimp.model.SourceFile;
import iit.du.ac.bd.misubeimp.support.DirectoryExplorer;

public class ListClassesExample {

	public static List<SourceFile> getAllClasses(File projectDir) {
		
		List<SourceFile> classFiles=new ArrayList<SourceFile>();
		new DirectoryExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
			//System.out.println(path);
			//System.out.println(Strings.repeat("=", path.length()));
			try {
				new VoidVisitorAdapter<Object>() {
					@Override
					public void visit(ClassOrInterfaceDeclaration n, Object arg) {
						super.visit(n, arg);
						String name=n.getName().toString();
						int k=0;
						System.out.println();
						//String modifiedPath=path.replace("/", "\\");
						//String absolutePath=projectDir+modifiedPath;
						//String name=n.getName().toString();
						//classFiles.add(new SourceFile(name+".java",name,absolutePath, "MyProject"));
						System.out.println(" * " + n.getName());
						//System.out.println(path);
					}
				}.visit(JavaParser.parse(file), null);
				//System.out.println(); // empty line
			} catch (IOException e) {
				new RuntimeException(e);
			}
		}).explore(projectDir);
		
		return classFiles;
	}

	public static void main(String[] args) {
		File projectDir = new File("D:\\NK");
		List<SourceFile> classFiles = getAllClasses(projectDir);
		int m = 0;
	}
}