package distribution;

import infrastructure.ServerRequestHandler;

import java.io.IOException;

import com.sun.xml.internal.ws.api.message.Packet;

import distribution.Marshaller;
import distribution.Message;

public class QueueInvoker {
	
	public void queueInvoke(String host, int port) throws IOException, Throwable{
		ServerRequestHandler srh = new ServerRequestHandler(port);
		byte[] msgToBeUnmarshalled = null;
		byte[] msgMarshalled = null;
		RequestPacket msgUnmarshalled = null;
		Marshaller marshaller = new Marshaller();
		
		QueueManager queueManager = new QueueManager(host, port);
		
		
		while(true) {
			msgToBeUnmarshalled = srh.receive();
			msgUnmarshalled = (RequestPacket) marshaller.unmarshall(msgToBeUnmarshalled);
			
			switch(msgUnmarshalled.getHeader().getOperation()) {
				case "":
					break;
				case "":
					break;
			}
		}
		
	}
	
}
