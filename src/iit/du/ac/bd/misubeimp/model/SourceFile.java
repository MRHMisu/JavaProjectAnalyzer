package iit.du.ac.bd.misubeimp.model;

import org.bson.Document;
import org.bson.types.ObjectId;

public class SourceFile {

	ObjectId sourceFileId;
	private String fileName;
	private String absolutePath;

	public SourceFile(String fileName, String absolutePath) {
		sourceFileId = new ObjectId();
		this.fileName = fileName;
		this.absolutePath = absolutePath;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "fileName: " + this.fileName + '\n' + "absolutePath: " + this.absolutePath + '\n';
	}

	public Document getBsonSourceFile() {
		Document document = new Document("fileName", this.fileName).append("absolutePath", this.absolutePath);
		return document;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

}
