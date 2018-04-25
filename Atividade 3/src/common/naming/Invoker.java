package common.naming;

import java.io.IOException;

import calculator.ReplyBody;
import calculator.ReplyHeader;
import calculator.ServerRequestHandler;
import client.ClientProxy;
import common.Marshaller;
import common.Message;
import common.MessageBody;
import common.MessageHeader;
import common.Termination;

public class Invoker {
	public void invoke(int port) throws IOException, Throwable {
		ServerRequestHandler srh = new ServerRequestHandler(port);
		byte[] msgToBeUnmarshalled = null;
		byte[] msgMarshalled = null;
		Message msgUnmarshalled = null;
		Marshaller marshaller = new Marshaller();
		Termination ter = new Termination();
		
		Naming rObj = new Naming();
		
		while(true) {
			msgToBeUnmarshalled = srh.receive();
			msgUnmarshalled = marshaller.unmarshall(msgToBeUnmarshalled);
			
			switch(msgUnmarshalled.getBody().getRequestHeader().getOperation()) {
				case "bind":
					String serviceNameBind = (String) msgUnmarshalled.getBody().getRequestBody().getParameters().get(0);
					ClientProxy clientProxyArg = (ClientProxy) msgUnmarshalled.getBody().getRequestBody().getParameters().get(1);
					int objectId = (int) msgUnmarshalled.getBody().getRequestBody().getParameters().get(2);
					rObj.bind(serviceNameBind, clientProxyArg, objectId);
					
					Message bindMsgToBeMarshalled = new Message(new MessageHeader("protocolo", 0, false, 0, 0), 
							new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(null)));
					
					msgMarshalled = marshaller.marshall(bindMsgToBeMarshalled);
					
					srh.send(msgMarshalled);
					break;
					
				case "lookup":
					String serviceName = (String) msgUnmarshalled.getBody().getRequestBody().getParameters().get(0);
					ter.setResult(rObj.lookup(serviceName));
					
					Message lookupMsgToBeMarshalled = new Message(new MessageHeader("protocolo", 0, false, 0, 0), 
							new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getResult())));
					
					msgMarshalled = marshaller.marshall(lookupMsgToBeMarshalled);
					
					srh.send(msgMarshalled);
					break;
					
				case "list":
					ter.setResult(rObj.list());
					
					Message listMsgToBeMarshalled = new Message(new MessageHeader("protocolo", 0, false, 0, 0), 
							new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getResult())));
					
					msgMarshalled = marshaller.marshall(listMsgToBeMarshalled);
					
					srh.send(msgMarshalled);
					break;
			}
		}
	}
}