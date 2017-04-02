package iit.du.ac.bd.misubeimp.analyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import iit.du.ac.bd.misubeimp.model.SourceFile;
import iit.du.ac.bd.misubeimp.support.DirectoryExplorer;

public class SourceFileCollector {

	private ObjectId projectId;

	public SourceFileCollector(ObjectId projectId) {
		this.projectId = projectId;
	}

	public static List<SourceFile> getAllFilesFromSourceDirectory(File directoryPath) {
		// String projectName=directoryPath.getName();
		List<SourceFile> sourceFiles = new ArrayList<SourceFile>();
		new DirectoryExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {

			String modifiedPath = path.replace("/", "\\");
			String absolutePath = directoryPath + modifiedPath;
			String fileName = new File(absolutePath).getName().toString();
			SourceFile sourceFile = new SourceFile(fileName, absolutePath);
			sourceFiles.add(sourceFile);

		}).explore(directoryPath);

		return sourceFiles;
	}

	/*public static void main(String[] args) {
		File projectDir = new File("D:\\Masters\\MastersLab\\MastersJavaWork\\IntefaceLoader");
		List<SourceFile> sourceFiles = getAllFilesFromSourceDirectory(projectDir);
		System.out.println(sourceFiles.size());
		for (SourceFile f : sourceFiles) {
			System.out.println(f);
		}

	}*/

}
