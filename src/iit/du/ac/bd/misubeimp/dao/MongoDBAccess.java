package iit.du.ac.bd.misubeimp.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import iit.du.ac.bd.misubeimp.model.Method;

public class MongoDBAccess {

	/*
	 * public static void main(String args[]) { getAccess(); }
	 */

	
	public static void getAccess(List<Method> methods) {
		try {

			MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoDatabase database = mongoClient.getDatabase("ImpDB");
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
}
