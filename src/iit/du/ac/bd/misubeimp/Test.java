package iit.du.ac.bd.misubeimp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

import iit.du.ac.bd.misubeimp.analyzer.MethodExtractor;
import iit.du.ac.bd.misubeimp.analyzer.SourceFileCollector;
import iit.du.ac.bd.misubeimp.model.Method;
import iit.du.ac.bd.misubeimp.model.SourceFile;

public class Test {

	public static void main(String[] args) throws Exception {

		String basePath = "D:\\Masters\\PaperDataset\\2014_SCAM_An Empirical Analysis of Bug Patterns\\projects-src\\projects\\projects";
		File directorypath = new File(basePath + "\\" + "ant");

		ObjectId projectId = new ObjectId();

		SourceFileCollector sourceFileCollector = new SourceFileCollector(projectId);
		List<SourceFile> sourceFiles = sourceFileCollector.getAllFilesFromSourceDirectory(directorypath);
		System.out.println(sourceFiles.size());
		List<Method> methods = new ArrayList<Method>();

		for (SourceFile c : sourceFiles) {
			MethodExtractor methodExtractor = new MethodExtractor(c.getSourceFileId(), c.getProjectId(),
					c.getSourcePath());
			methods.addAll(methodExtractor.getAllMethods(new File(c.getAbsolutePath())));

		}
		System.out.println(methods.size());
		

		// MongoDBAccess.getAccess(methods);

	}

}
