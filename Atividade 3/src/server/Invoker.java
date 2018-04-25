package server;

import java.io.IOException;

import common.Marshaller;
import common.Message;
import common.MessageBody;
import common.MessageHeader;
import common.Termination;
import client.ClientProxy;

public class Invoker {
	public void invoke(ClientProxy clientProxy) throws IOException, Throwable {
		ServerRequestHandler srh = new ServerRequestHandler(clientProxy.getPort());
		byte[] msgToBeUnmarshalled = null;
		byte[] msgMarshalled = null;
		Message msgUnmarshalled = null;
		Marshaller marshaller = new Marshaller();
		Termination ter = new Termination();
		
		Calculator rObj = new Calculator();
		
		while(true) {
			msgToBeUnmarshalled = srh.receive();
			msgUnmarshalled = marshaller.unmarshall(msgToBeUnmarshalled);
			
			switch(msgUnmarshalled.getBody().getRequestHeader().getOperation()) {
				case "add":
					float addP1 = (float) msgUnmarshalled.getBody().getRequestBody().getParameters().get(0);
					float addP2 = (float) msgUnmarshalled.getBody().getRequestBody().getParameters().get(1);
					ter.setResult(rObj.add(addP1, addP2));
					
					Message addMsgToBeMarshalled = new Message(new MessageHeader("protocolo", 0, false, 0, 0), 
							new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getResult())));
					
					msgMarshalled = marshaller.marshall(addMsgToBeMarshalled);
					
					srh.send(msgMarshalled);
					return;
					
				case "sub":
					float subP1 = (float) msgUnmarshalled.getBody().getRequestBody().getParameters().get(0);
					float subP2 = (float) msgUnmarshalled.getBody().getRequestBody().getParameters().get(1);
					ter.setResult(rObj.sub(subP1, subP2));
					
					Message subMsgToBeMarshalled = new Message(new MessageHeader("protocolo", 0, false, 0, 0), 
							new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getResult())));
					
					msgMarshalled = marshaller.marshall(subMsgToBeMarshalled);
					
					srh.send(msgMarshalled);
					return;
			}
		}
	}
}
