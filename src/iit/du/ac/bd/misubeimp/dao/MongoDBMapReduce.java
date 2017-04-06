package iit.du.ac.bd.misubeimp.dao;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MapReduceIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBMapReduce {

	public static void testMapReduce(String mapFunction,String reduceFunction, String databaseName, String collectionName) {
		try {

			MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoDatabase database = mongoClient.getDatabase(databaseName);
			System.out.println("Connect to DB");
			MongoCollection<Document> collection = database.getCollection(collectionName);
			MapReduceIterable<Document> reducedDocumets=collection.mapReduce(mapFunction, reduceFunction);
			for (Document o : reducedDocumets) {
				System.out.println(o.toString());
			}
			mongoClient.close();
			System.out.println("Disconnected From DB");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public static void main(String[] args) {

		String mapFunction = "function() { " +

				"var category; " +

				"if ( this.methodBody.length >= 5 ) " +

				"category =this.methodBody; " +

				"else " +

				"category = this.methodBody; " +

				"emit(category, {name: this.name});}";

		String reduceFunction = "function(key, values) { " +

				"var sum = 0; " +

				"values.forEach(function(doc) { " +

				"sum += 1; " +

				"}); " +

				"return {methods: sum};} ";
		//String reduce="";
		String databaseName="RatersClone";
		String collectionName="Methods";
		testMapReduce( mapFunction,reduceFunction,databaseName,collectionName);

	}
}
