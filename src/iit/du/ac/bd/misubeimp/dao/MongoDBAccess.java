package iit.du.ac.bd.misubeimp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import iit.du.ac.bd.misubeimp.model.Method;
import iit.du.ac.bd.misubeimp.model.Project;
import iit.du.ac.bd.misubeimp.model.SourceFile;

public class MongoDBAccess {

	/*
	 * public static void main(String args[]) { getAccess(); }
	 */

	
	public static void getAccess(Set<Method> methods) {
		try {

			MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoDatabase database = mongoClient.getDatabase("CodeCloneDB");
			System.out.println("Connect to database successfully");
			System.out.println(database.getName());
			MongoCollection<Document> collection = database.getCollection("Methods");
			System.out.println("Collection mycol selected successfully");

			List<Document> documents = new ArrayList<Document>();
			for (Method m : methods) {
				documents.add(m.getBsonMethod());
			}
			collection.insertMany(documents);
			mongoClient.close();
			System.out.println("Disconnected From DB");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public static ObjectId getIDFromSourceFileInserting(SourceFile sourceFile) {
		
		ObjectId sourceFileID=null;
		try {

			MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoDatabase database = mongoClient.getDatabase("CodeCloneDB");
			System.out.println("Connect to database successfully");
			
			MongoCollection<Document> collection = database.getCollection("SourceFiles");
			
			Document basonSourceFile=sourceFile.getBsonSourceFile();
			collection.insertOne(basonSourceFile);
			sourceFileID=(ObjectId)basonSourceFile.get( "_id" );
		
			mongoClient.close();
			System.out.println("Disconnected From DB");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return sourceFileID;
	}
	
	public static ObjectId  getIDFromProjectInserting(Project project) {
		ObjectId projectID=null;
		try {

			MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoDatabase database = mongoClient.getDatabase("CodeCloneDB");
			System.out.println("Connect to database successfully");
			MongoCollection<Document> collection = database.getCollection("Projects");
			
			Document basonProject=project.getBsonProject();
			collection.insertOne(basonProject);
			 projectID=(ObjectId)basonProject.get( "_id" );
			
			mongoClient.close();
			System.out.println("Disconnected From DB");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return projectID;
	}
	
}
