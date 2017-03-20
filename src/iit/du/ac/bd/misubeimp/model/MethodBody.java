package iit.du.ac.bd.misubeimp.model;

import org.bson.Document;

public class MethodBody {

	private int startLine;
	private int endLine;
	private int length;
	private String body;

	public MethodBody(int startLine, int endLine, int length, String body) {
		this.startLine = startLine;
		this.endLine = endLine;
		this.length = length;
		this.body = body;
	}

	public Document getBsonMethodBody() {
		Document document = new Document("startLine", startLine).append("endLine", this.endLine)
				.append("length", length).append("body", body);

		return document;
	}

}
