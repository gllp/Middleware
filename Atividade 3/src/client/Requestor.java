package client;

import java.io.IOException;
import java.net.UnknownHostException;

import common.Marshaller;
import common.Message;
import common.MessageBody;
import common.MessageHeader;
import common.Termination;

public class Requestor {
	public Termination invoke(Invocation inv) throws UnknownHostException, IOException, Throwable {
		ClientRequestHandler crh = new ClientRequestHandler(inv.getIpAddress(), inv.getPortNumber());
		Marshaller marshaller = new Marshaller();
		Termination termination = new Termination();
		byte[] msgMarshalled = null;
		Message msgUnmarshalled = null;
		byte[] msgToBeUnmarshalled = null;
		
		RequestHeader requestHeader = new RequestHeader("", 0, true, 0, inv.getOperationName());
		RequestBody requestBody = new RequestBody(inv.getParameters());
		MessageHeader messageHeader = new MessageHeader("MIOP", 0, false, 0, 0);
		MessageBody messageBody = new MessageBody(requestHeader, requestBody, null, null);
		Message msgToBeMarshalled = new Message(messageHeader, messageBody);
		
		msgMarshalled = marshaller.marshall(msgToBeMarshalled);
		crh.send(msgMarshalled);

		msgToBeUnmarshalled = crh.receive();
		msgUnmarshalled = marshaller.unmarshall(msgToBeUnmarshalled);
		
		termination.setResult(msgUnmarshalled.getBody().getReplyBody().getOperationResult());
		
		return termination;
	} 
}
