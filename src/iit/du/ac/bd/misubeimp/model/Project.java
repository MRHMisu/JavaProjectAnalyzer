package iit.du.ac.bd.misubeimp.model;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Project {

	private ObjectId projectId;
	private String name;
	private String description;
	private String version;
	private int  numberOfSourceFiles;
	private List<String> sourceFileNames;
	

	public Project(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Name: " + name + '\n' + "Description: " + description;
	}

	public Document getBsonProject() {
		Document document = new Document("name", this.name).append("description", description);
		return document;
	}

}
