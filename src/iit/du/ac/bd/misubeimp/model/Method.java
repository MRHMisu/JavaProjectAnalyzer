package iit.du.ac.bd.misubeimp.model;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Method {

	private String Modifier;
	private String ReturnType;
	private String Name;
	private String Signature;
	private ObjectId SourceFileId;
	private ObjectId ProjectId;

	public Method(String modifier, String returnType, String name, String signature,ObjectId sourceFileId, ObjectId projectId) {

		this.Modifier = modifier;
		this.ReturnType = returnType;
		this.Name = name;
		this.Signature = signature;
		this.ProjectId=projectId;
		this.SourceFileId=sourceFileId;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "modifier: " + Modifier + '\n' + "returnType: " + ReturnType + '\n' + "name: " + Name + '\n'
				+ "signature: " + Signature + '\n'+"sourceFileId:"+SourceFileId+'\n'+"projectId :"+ProjectId;
	}

	public Document getBsonMethod() {
		Document document = new Document("modifier", this.Modifier).append("returnType", ReturnType)
				.append("name", Name).append("signature", Signature).append("sourceFileId",this.SourceFileId)
				.append("projectId",this.ProjectId);
		return document;
	}

}
