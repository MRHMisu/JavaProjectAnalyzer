package misu.be.imp.bd.ac.du.iit;

import org.bson.Document;

public class Method {

	private String Modifier;
	private String ReturnType;
	private String Name;
	private String Signature;

	public Method(String modifier, String returnType, String name, String signature) {

		Modifier = modifier;
		ReturnType = returnType;
		Name = name;
		Signature = signature;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "modifier: " + Modifier + '\n' + "returnType: " + ReturnType + '\n' + "name: " + Name + '\n'
				+ "signature: " + Signature + '\n';
	}

	public Document getBsonNMethod() {
		Document document = new Document("modifier", this.Modifier).append("returnType", ReturnType)
				.append("name", Name).append("signature", Signature);
		return document;
	}

}
