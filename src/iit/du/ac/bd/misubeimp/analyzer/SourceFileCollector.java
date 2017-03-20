package iit.du.ac.bd.misubeimp.analyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import iit.du.ac.bd.misubeimp.model.SourceFile;
import iit.du.ac.bd.misubeimp.support.DirectoryExplorer;

public class SourceFileCollector {

	private ObjectId projectId;
	
	
	public SourceFileCollector(ObjectId projectId) {
		this.projectId = projectId;
	}


	public List<SourceFile> getAllFilesFromSourceDirectory(File directoryPath) {
		//String projectName=directoryPath.getName();
		List<SourceFile> sourceFiles = new ArrayList<SourceFile>();
		new DirectoryExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
			try {
				new VoidVisitorAdapter<Object>() {
					@Override
					public void visit(ClassOrInterfaceDeclaration n, Object arg) {
						super.visit(n, arg);
						
						String modifiedPath = path.replace("/", "\\");
						String absolutePath = directoryPath + modifiedPath;
						String fileName = n.getName().toString();
						
						
					}
				}.visit(JavaParser.parse(file), null);
			} catch (IOException e) {
				new RuntimeException(e);
			}
		}).explore(directoryPath);

		return sourceFiles;
	}

	/*public static void main(String[] args) {
		File projectDir = new File("C:\\Users\\MisuBeImp\\Downloads\\UltimateCalculator-master\\UltimateCalculator-master");
		List<ClassFile> classFiles = getAllFilesFromSourceDirectory(projectDir);
		int m = 0;
	}*/

}
