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

public class SourceFileCollector {

	public List<ClassFile> getAllFilesFromSourceDirectory(File directoryPath) {
		String projectName=directoryPath.getName();
		List<ClassFile> classFiles = new ArrayList<ClassFile>();
		new DirectoryExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
			try {
				new VoidVisitorAdapter<Object>() {
					@Override
					public void visit(ClassOrInterfaceDeclaration n, Object arg) {
						super.visit(n, arg);
						String modifiedPath = path.replace("/", "\\");
						String absolutePath = directoryPath + modifiedPath;
						String name = n.getName().toString();
						classFiles.add(new ClassFile(name + ".java", name, absolutePath,projectName));
					}
				}.visit(JavaParser.parse(file), null);
			} catch (IOException e) {
				new RuntimeException(e);
			}
		}).explore(directoryPath);

		return classFiles;
	}

	/*public static void main(String[] args) {
		File projectDir = new File("C:\\Users\\MisuBeImp\\Downloads\\UltimateCalculator-master\\UltimateCalculator-master");
		List<ClassFile> classFiles = getAllFilesFromSourceDirectory(projectDir);
		int m = 0;
	}*/

}
