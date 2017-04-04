package iit.du.ac.bd.misubeimp.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import iit.du.ac.bd.misubeimp.model.Method;
import iit.du.ac.bd.misubeimp.model.Project;
import iit.du.ac.bd.misubeimp.model.SourceFile;



public class DataBaseAccess {


	public static void getRequiredMethods(int k,String fileName, int startLine, int endLine, String databaseName,

			String collectionName) {
		try {
			
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoDatabase database = mongoClient.getDatabase(databaseName);
			System.out.println("Connect to DB"+k+"---times");
			MongoCollection<Document> collection = database.getCollection(collectionName);
			Document finalQuery = new Document();
			List<Document> fieldQuery = new ArrayList<Document>();

			fieldQuery.add(new Document("fileName", fileName));
			fieldQuery.add(new Document("methodBody.startLine", new Document("$lt", startLine)));
			//fieldQuery.add(new Document("methodBody.endLine", new Document("$gt", endLine)));
			finalQuery.put("$and", fieldQuery);

			FindIterable<Document> iterable = collection.find(finalQuery);

			iterable.forEach(new Block<Document>() {
				@Override
				public void apply(final Document document) {
					System.out.println(k+"--"+document.get("_id").toString());
				}
			});

			
			mongoClient.close();
			System.out.println("Disconnected From DB");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public static void insertProject(Project project, String databaseName, String collectionName) {
		try {

			MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoDatabase database = mongoClient.getDatabase(databaseName);
			System.out.println("Connect to DB");
			MongoCollection<Document> collection = database.getCollection(collectionName);
			Document documentProject = project.getBsonProject();
			collection.insertOne(documentProject);
			mongoClient.close();
			System.out.println("Disconnected From DB");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public static void insertSourceFile(SourceFile sourceFile, String databaseName, String collectionName) {
		try {

			MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoDatabase database = mongoClient.getDatabase(databaseName);
			System.out.println("Connect to DB");
			MongoCollection<Document> collection = database.getCollection(collectionName);
			Document documents = sourceFile.getBsonSourceFile();

			collection.insertOne(documents);
			mongoClient.close();
			System.out.println("Disconnected From DB");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public static void insertMethods(List<Method> methods, String databaseName, String collectionName) {
		try {

			MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoDatabase database = mongoClient.getDatabase(databaseName);
			System.out.println("Connect to DB");
			MongoCollection<Document> collection = database.getCollection(collectionName);
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

}
