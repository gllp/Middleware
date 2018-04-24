package common;

import java.io.Serializable;

import server.ReplyBody;
import server.ReplyHeader;
import client.RequestBody;
import client.RequestHeader;

public class MessageBody implements Serializable{
	private static final long serialVersionUID = 1L;
	private RequestHeader requestHeader;
	private RequestBody requestBody;
	private ReplyHeader replyHeader;
	private ReplyBody replyBody;
	
	public MessageBody(RequestHeader requestHeader, RequestBody requestBody,
			ReplyHeader replyHeader, ReplyBody replyBody) {
		this.requestHeader = requestHeader;
		this.requestBody = requestBody;
		this.replyHeader = replyHeader;
		this.replyBody = replyBody;
	}

	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}

	public RequestBody getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(RequestBody requestBody) {
		this.requestBody = requestBody;
	}

	public ReplyHeader getReplyHeader() {
		return replyHeader;
	}

	public void setReplyHeader(ReplyHeader replyHeader) {
		this.replyHeader = replyHeader;
	}

	public ReplyBody getReplyBody() {
		return replyBody;
	}

	public void setReplyBody(ReplyBody replyBody) {
		this.replyBody = replyBody;
	}
}
