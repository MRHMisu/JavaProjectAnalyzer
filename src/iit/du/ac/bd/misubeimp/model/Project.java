package iit.du.ac.bd.misubeimp.model;

import org.bson.Document;

public class Project {

	private String Name;
	private String Description;

	public Project(String name, String description) {
		this.Name = name;
		this.Description = description;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Name: " + Name + '\n' + "Description: " + Description;
	}

	public Document getBsonProject() {
		Document document = new Document("name", this.Name).append("description", Description);
		return document;
	}

}
