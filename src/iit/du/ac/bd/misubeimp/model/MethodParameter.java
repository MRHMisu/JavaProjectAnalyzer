package iit.du.ac.bd.misubeimp.model;

import org.bson.Document;

public class MethodParameter {

	private int Order;
	private String Name;
	private String Type;

	public MethodParameter(int order, String name, String type) {
		this.Order = order;
		this.Name = name;
		this.Type = type;
	}
	public String getOrder() {
		return Order+"";
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "order:" +Order+'\n' +"name: " + Name + '\n' + "type: " + Type;
	}

	public Document getParameterModel() {
		Document document = new Document("order",this.Order).append("name", this.Name).append("type", this.Type);
		return document;
	}

}
