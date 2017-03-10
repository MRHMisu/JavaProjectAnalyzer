package iit.du.ac.bd.misubeimp.model;

import org.bson.Document;

public class Method {

	private String Modifier;
	private String ReturnType;
	private String Name;
	private String Signature;

	public Method(String modifier, String returnType, String name, String signature) {

		this.Modifier = modifier;
		this.ReturnType = returnType;
		this.Name = name;
		this.Signature = signature;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "modifier: " + Modifier + '\n' + "returnType: " + ReturnType + '\n' + "name: " + Name + '\n'
				+ "signature: " + Signature + '\n';
	}

	public Document getBsonMethod() {
		Document document = new Document("modifier", this.Modifier).append("returnType", ReturnType)
				.append("name", Name).append("signature", Signature);
		return document;
	}

}
