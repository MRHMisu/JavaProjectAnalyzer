package iit.du.ac.bd.misubeimp.analyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import iit.du.ac.bd.misubeimp.model.SourceFile;
import iit.du.ac.bd.misubeimp.support.DirectoryExplorer;

public class SourceFileCollector {

	private ObjectId projectId;
	private String projectName;

	public SourceFileCollector(ObjectId projectId, String projectName) {
		this.projectId = projectId;
		this.projectName = projectName;
	}

	public List<SourceFile> getAllFilesFromSourceDirectory(File directoryPath) {
		// String projectName=directoryPath.getName();
		List<SourceFile> sourceFiles = new ArrayList<SourceFile>();
		new DirectoryExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {

			String modifiedPath = path.replace("/", "\\");
			String absolutePath = directoryPath + modifiedPath;
			String sourcepath = modifiedPath.replace("\\", ".").substring(1).replace(".java", "");
			;
			String fileName = new File(absolutePath).getName().toString();
			SourceFile sourceFile = new SourceFile(this.projectId, projectName, fileName, absolutePath, sourcepath);
			sourceFiles.add(sourceFile);

		}).explore(directoryPath);

		return sourceFiles;
	}

	/*
	 * public static void main(String[] args) { File projectDir = new File(
	 * "D:\\Masters\\PaperDataset\\2017_ESE_Raters_Reliability\\materials\\ese15-charpentier-al\\data\\fastr"
	 * ); ObjectId projectId = new ObjectId(); List<SourceFile> sourceFiles =
	 * new
	 * SourceFileCollector(projectId).getAllFilesFromSourceDirectory(projectDir)
	 * ; System.out.println(sourceFiles.size()); for (SourceFile f :
	 * sourceFiles) { System.out.println(f); }
	 * 
	 * }
	 */

}
