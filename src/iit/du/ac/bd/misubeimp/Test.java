package iit.du.ac.bd.misubeimp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import iit.du.ac.bd.misubeimp.analyzer.MethodExtractor;
import iit.du.ac.bd.misubeimp.analyzer.SourceFileCollector;
import iit.du.ac.bd.misubeimp.dao.MongoDBAccess;
import iit.du.ac.bd.misubeimp.model.ClassFile;
import iit.du.ac.bd.misubeimp.model.Method;

public class Test {

	public static void main(String[] args) throws Exception {

		File directorypath = new File(
				"C:\\Users\\MisuBeImp\\Downloads\\UltimateCalculator-master\\UltimateCalculator-master");
		SourceFileCollector sourceFileCollector = new SourceFileCollector();

		List<ClassFile> classFiles = sourceFileCollector.getAllFilesFromSourceDirectory(directorypath);
		List<Method> methods = new ArrayList<Method>();
		MethodExtractor methodExtractor = new MethodExtractor();
		System.out.println(methods.size());
		for (ClassFile c : classFiles) {
			methods.addAll(methodExtractor.getAllMethods(new File(c.getAbsolutePath())));
			System.out.println(methods.size());
		}
		System.out.println(methods.size());
		int v = 0;
		// MongoDBAccess.getAccess(methods);

	}

}
