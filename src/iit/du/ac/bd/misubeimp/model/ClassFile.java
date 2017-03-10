package iit.du.ac.bd.misubeimp.model;

import org.bson.Document;

public class ClassFile {

	private String FileName;
	private String ClassName;
	private String AbsolutePath;
	private String ProjectName;

	public ClassFile(String fileName, String className, String absolutePath, String projectName) {
		this.FileName = fileName;
		this.ClassName = className;
		this.AbsolutePath = absolutePath;
		this.ProjectName = projectName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "fileName: " + this.FileName + '\n' + "className: " + this.ClassName + '\n' + "absolutePath: "
				+ this.AbsolutePath + '\n' + "projectName: " + this.ProjectName + '\n';
	}

	public Document getBsonClassFile() {
		Document document = new Document("fileName", this.FileName).append("className", this.ClassName)
				.append("absolutePath", this.AbsolutePath).append("projectName", this.ProjectName);
		return document;
	}

}
