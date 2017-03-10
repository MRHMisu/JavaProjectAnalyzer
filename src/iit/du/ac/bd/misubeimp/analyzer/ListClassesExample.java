package iit.du.ac.bd.misubeimp.analyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import iit.du.ac.bd.misubeimp.model.ClassFile;
import iit.du.ac.bd.misubeimp.support.DirectoryExplorer;

public class ListClassesExample {

	public static List<ClassFile> getAllClasses(File projectDir) {
		
		List<ClassFile> classFiles=new ArrayList<ClassFile>();
		new DirectoryExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
			//System.out.println(path);
			//System.out.println(Strings.repeat("=", path.length()));
			try {
				new VoidVisitorAdapter<Object>() {
					@Override
					public void visit(ClassOrInterfaceDeclaration n, Object arg) {
						super.visit(n, arg);
						String modifiedPath=path.replace("/", "\\");
						String absolutePath=projectDir+modifiedPath;
						String name=n.getName().toString();
						classFiles.add(new ClassFile(name+".java",name,absolutePath, "MyProject"));
						//System.out.println(" * " + n.getName());
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
		File projectDir = new File("D:\\Masters Lab\\ParsingJavaProject");
		List<ClassFile> classFiles = getAllClasses(projectDir);
		int m = 0;
	}
}