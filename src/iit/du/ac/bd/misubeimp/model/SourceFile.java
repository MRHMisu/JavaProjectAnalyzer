package iit.du.ac.bd.misubeimp.model;

import org.bson.Document;
import org.bson.types.ObjectId;

public class SourceFile {

	ObjectId projectId;
	ObjectId sourceFileId;
	private String fileName;
	private String absolutePath;
	private String sourcePath;

	public SourceFile(ObjectId projectId, String fileName, String absolutePath, String sourcePath) {
		sourceFileId = new ObjectId();
		this.projectId = projectId;
		this.fileName = fileName;
		this.absolutePath = absolutePath;
		this.sourcePath = sourcePath;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public String getSourcePath() {
		return sourcePath;
	}

	public ObjectId getSourceFileId() {
		return sourceFileId;
	}

	public ObjectId getProjectId() {
		return projectId;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "fileName: " + this.fileName + '\n' + "absolutePath: " + this.absolutePath + '\n' + "sourcePath: "
				+ this.sourcePath + '\n';
	}

	public Document getBsonSourceFile() {
		Document document = new Document("_id", this.sourceFileId).append("projectId", this.projectId)
				.append("fileName", this.fileName).append("sourcePath", this.sourcePath);
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
