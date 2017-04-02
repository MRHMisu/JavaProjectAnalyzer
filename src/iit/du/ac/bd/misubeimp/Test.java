package iit.du.ac.bd.misubeimp;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;

import iit.du.ac.bd.misubeimp.analyzer.MethodExtractor;
import iit.du.ac.bd.misubeimp.analyzer.SourceFileCollector;
import iit.du.ac.bd.misubeimp.dao.MongoDBAccess;
import iit.du.ac.bd.misubeimp.model.Method;
import iit.du.ac.bd.misubeimp.model.Project;
import iit.du.ac.bd.misubeimp.model.SourceFile;

public class Test {

	public static void main(String[] args) throws Exception {

		String basePath="D:\\Masters\\PaperDataset\\DataSet_2014_SCAM_An Empirical Analysis of Bug Patterns in Java Code Clones\\projects-src\\projects\\projects";
		File directorypath = new File(basePath+"\\"+"ant");
		
		ObjectId projectID =new ObjectId();
		
		//ObjectId projectID=MongoDBAccess.getIDFromProjectInserting(new Project("Misu", "Optional"));
		SourceFileCollector sourceFileCollector = new SourceFileCollector(projectID);
		
		
		List<SourceFile> sourceFiles = SourceFileCollector.getAllFilesFromSourceDirectory(directorypath);
		System.out.println(sourceFiles.size());
		Set<Method> methods = new HashSet<Method>();
		
		System.out.println(methods.size());
		for (SourceFile c : sourceFiles) {
			ObjectId sourceFileID=new ObjectId(); //MongoDBAccess.getIDFromSourceFileInserting(c);
			//MethodExtractor methodExtractor = new MethodExtractor(sourceFileID,c.getProjectID());
			//methods.addAll(methodExtractor.getAllMethods(new File(c.getAbsolutePath())));
			System.out.println(methods.size());
		}
		System.out.println(methods.size());
		int v = 0;
		
		
		 //MongoDBAccess.getAccess(methods);

	}

}
