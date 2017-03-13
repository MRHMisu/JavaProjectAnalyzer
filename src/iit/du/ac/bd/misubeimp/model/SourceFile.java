package iit.du.ac.bd.misubeimp.model;

import org.bson.Document;
import org.bson.types.ObjectId;

public class SourceFile {

	private String FileName;
	private String ClassName;
	private String AbsolutePath;
	private ObjectId ProjectID;
	

	
	public SourceFile(String fileName, String className, String absolutePath, ObjectId projectID) {
		this.FileName = fileName;
		this.ClassName = className;
		this.AbsolutePath = absolutePath;
		this.ProjectID = projectID;
	}

	public String getAbsolutePath() {
		return AbsolutePath;
	}
	public ObjectId getProjectID() {
		return ProjectID;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "fileName: " + this.FileName + '\n' + "className: " + this.ClassName + '\n' + "absolutePath: "
				+ this.AbsolutePath + '\n' + "projectID: " + this.ProjectID + '\n';
	}

	public Document getBsonSourceFile() {
		Document document = new Document("fileName", this.FileName).append("className", this.ClassName)
				.append("absolutePath", this.AbsolutePath).append("projectID", this.ProjectID);
		return document;
	}

}
