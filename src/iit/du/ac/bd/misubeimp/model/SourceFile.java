package iit.du.ac.bd.misubeimp.model;

import java.util.Set;

import org.bson.Document;
import org.bson.types.ObjectId;

public class SourceFile {

	ObjectId sourceFileId;
	private String fileName;
	private String fileType;
	private Set<String> className;
	private String absolutePath;
	

	
	public SourceFile(String fileName, Set<String> className, String absolutePath) {
		sourceFileId=new ObjectId();
		this.fileName = fileName;
		this.className = className;
		this.absolutePath = absolutePath;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "fileName: " + this.fileName + '\n' + "className: " + this.className + '\n' + "absolutePath: "
				+ this.absolutePath + '\n' ;
	}

	public Document getBsonSourceFile() {
		Document document = new Document("fileName", this.fileName).append("className", this.className)
				.append("absolutePath", this.absolutePath);
		return document;
	}

}
