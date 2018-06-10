package distribution;

import infrastructure.ClientRequestHandler;
import infrastructure.ServerRequestHandler;

import java.io.IOException;
import java.util.ArrayList;

public class QueueManagerProxy implements IQueueManager {
	String host;
	int port;
	private String queueName = null;

	public QueueManagerProxy(String host, int port, String queueName) {
		this.host = host;
		this.port = port;
		this.queueName = queueName;
	}
	
	public void send(Object obj) throws IOException, InterruptedException {
		ClientRequestHandler crh = new ClientRequestHandler(host, port, false);
		
		Marshaller marshaller = new Marshaller();
		RequestPacket packet = new RequestPacket();
		Message message = new Message();
		
		message.setHeader(new MessageHeader(this.queueName));
		message.setBody(new MessageBody(obj));
		
		ArrayList<Object> parameters = new ArrayList<Object>(0);
		
		packet.setHeader(new RequestPacketHeader("publish"));
		packet.setBody(new RequestPacketBody(parameters, message));
		
		crh.send(marshaller.marshall((Object) packet));
	}

	public Object receive() throws IOException, InterruptedException, ClassNotFoundException {
		ServerRequestHandler srh = new ServerRequestHandler(port);
		
		Marshaller marshaller = new Marshaller();
		
		byte[] response = srh.receive();
		
		return marshaller.unmarshall(response);
	}
	
	public void subscribe() throws IOException, InterruptedException {
		ClientRequestHandler crh = new ClientRequestHandler(host, port, false);
		
		Marshaller marshaller = new Marshaller();
		RequestPacket packet = new RequestPacket();
		Message message = new Message();
		
		message.setHeader(new MessageHeader(this.queueName));
		message.setBody(new MessageBody());
		
		ArrayList<Object> parameters = new ArrayList<Object>(0);
		
		packet.setHeader(new RequestPacketHeader("subscribe"));
		packet.setBody(new RequestPacketBody(parameters, message));
		
		crh.send(marshaller.marshall((Object) packet));
	}
}
