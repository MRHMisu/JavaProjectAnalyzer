package iit.du.ac.bd.misubeimp;

import java.io.File;
import java.util.List;

import iit.du.ac.bd.misubeimp.analyzer.MethodExtractor;
import iit.du.ac.bd.misubeimp.analyzer.SourceFileCollector;
import iit.du.ac.bd.misubeimp.dao.DataBaseAccess;
import iit.du.ac.bd.misubeimp.example.CodeChecker;
import iit.du.ac.bd.misubeimp.model.Method;
import iit.du.ac.bd.misubeimp.model.Project;
import iit.du.ac.bd.misubeimp.model.SourceFile;

public class Test {

	public static void main(String[] args) throws Exception {

		String basePath = "D:\\Masters\\PaperDataset\\2017_ESE_Raters_Reliability\\materials\\ese15-charpentier-al\\data";
		String projectName = "gumtree";
		File directorypath = new File(basePath + "\\" + projectName);

		Project project = new Project(projectName);
		DataBaseAccess.insertProject(project, "RatersClone", "Projects");
		SourceFileCollector sourceFileCollector = new SourceFileCollector(project.getProjectId(),
				project.getProjectName());

		List<SourceFile> sourceFiles = sourceFileCollector.getAllFilesFromSourceDirectory(directorypath);

		// checking code;
		/*
		 * for (SourceFile c : sourceFiles) {
		 * CodeChecker.checkCode(c.getAbsolutePath()); }
		 */

		for (SourceFile c : sourceFiles) {
			DataBaseAccess.insertSourceFile(c, "RatersClone", "SourceFiles");
			MethodExtractor methodExtractor = new MethodExtractor(c.getProjectId(), c.getProjectName(),
					c.getSourceFileId(), c.getSourceFileName(), c.getSourcePath());
			List<Method> methods = methodExtractor.getAllMethods(new File(c.getAbsolutePath()));
			if (methods.size() > 0) {
				DataBaseAccess.insertMethods(methods, "RatersClone", "Methods");
			}
		}
		System.out.println("Finished");

	}

}
