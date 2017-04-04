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

		String basePath = "D:\\Masters\\PaperDataset\\2014_SCAM_An Empirical Analysis of Bug Patterns\\projects-src\\projects\\projects";
		String projectName = "berkeleyparser";
		File directorypath = new File(basePath + "\\" + projectName);

		Project project = new Project(projectName);
		 DataBaseAccess.insertProject(project, "BugClone", "Projects");
		SourceFileCollector sourceFileCollector = new SourceFileCollector(project.getProjectId());

		List<SourceFile> sourceFiles = sourceFileCollector.getAllFilesFromSourceDirectory(directorypath);

		// checking code;
		/*
		 * for (SourceFile c : sourceFiles) {
		 * CodeChecker.checkCode(c.getAbsolutePath()); }
		 */

		for (SourceFile c : sourceFiles) {
			DataBaseAccess.insertSourceFile(c, "BugClone", "SourceFiles");
			MethodExtractor methodExtractor = new MethodExtractor(c.getSourceFileId(), c.getProjectId(),
					c.getSourcePath());
			List<Method> methods = methodExtractor.getAllMethods(new File(c.getAbsolutePath()));
			if (methods.size() > 0) {
				DataBaseAccess.insertMethods(methods, "BugClone", "Methods");
			}
		}
		System.out.println("Finished");

	}

}
