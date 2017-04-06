package iit.du.ac.bd.misubeimp.model;

import org.bson.Document;
import org.bson.types.ObjectId;

public class SourceFile {

	ObjectId projectId;
	private String projectName;
	ObjectId sourceFileId;
	private String fileName;
	private String absolutePath;
	private String sourcePath;

	public SourceFile(ObjectId projectId, String projectName, String fileName, String absolutePath, String sourcePath) {
		sourceFileId = new ObjectId();
		this.projectId = projectId;
		this.projectName = projectName;
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

	public String getSourceFileName() {
		return this.fileName;
	}

	public ObjectId getProjectId() {
		return projectId;
	}

	public String getProjectName() {
		return this.projectName;

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "fileName: " + this.fileName + '\n' + "absolutePath: " + this.absolutePath + '\n' + "sourcePath: "
				+ this.sourcePath + '\n';
	}

	public Document getBsonSourceFile() {
		Document document = new Document("_id", this.sourceFileId).append("projectId", this.projectId)
				.append(projectName, projectName).append("fileName", this.fileName)
				.append("sourcePath", this.sourcePath);
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
