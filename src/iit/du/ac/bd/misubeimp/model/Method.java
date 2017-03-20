package iit.du.ac.bd.misubeimp.model;

import java.util.Set;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Method {

	private ObjectId methodId;
	private Set<String> modifiers;
	private String returnType;
	private String name;
	private String signature;
	private Set<MethodParameter> parameters;
	private MethodBody methodbody;

	public Method(Set<String> modifiers, String returnType, String name, String signature,Set<MethodParameter> parameters, MethodBody methodBody) {

		this.methodId=new ObjectId();
		this.modifiers = modifiers;
		this.returnType = returnType;
		this.name = name;
		this.signature = signature;
		this.parameters = parameters;
		this.methodbody=methodBody;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "modifier: " + modifiers + '\n' + "returnType: " + returnType + '\n' + "name: " + name + '\n'
				+ "signature: " + signature + '\n';
	}

	public Document getBsonMethod() {
		Document document = new Document("_id",this.methodId).append("modifier", this.modifiers).append("returnType", returnType)
				.append("name", name).append("signature", signature);

		document.put("parameters", this.getBsonParameterList());
		document.put("methodBody",this.methodbody.getBsonMethodBody());
		
		return document;
	}

	public Document getBsonParameterList()
	{
		Document document = new Document();
		for( MethodParameter m:this.parameters)
		{
			document.put(m.getOrder(),m.getParameterModel());	
		}
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
