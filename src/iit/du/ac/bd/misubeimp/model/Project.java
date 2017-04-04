package iit.du.ac.bd.misubeimp.model;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Project {

	private ObjectId projectId;
	private String name;

	public Project(String name) {
		this.projectId = new ObjectId();
		this.name = name;
	}

	public ObjectId getProjectId() {
		return this.projectId;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Name: " + name + '\n';
	}

	public Document getBsonProject() {
		Document document = new Document("_id", this.projectId).append("name", this.name);
		return document;
	}

}
