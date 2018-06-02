package distribution;

import infrastructure.ClientRequestHandler;

import java.io.IOException;
import java.util.ArrayList;

public class QueueManagerProxy implements IQueueManager {
	private String queueName = null;

	public QueueManagerProxy(String queueName) {
		this.queueName = queueName;
	}
	
	public void send(String str) throws IOException, InterruptedException {
		ClientRequestHandler crh = new ClientRequestHandler("localhost", 1313, false);
		
		Marshaller marshaller = new Marshaller();
		RequestPacket packet = new RequestPacket();
		Message message = new Message();
		
		message.setHeader(new MessageHeader(this.queueName));
		message.setBody(new MessageBody(str));
		
		ArrayList<Object> parameters = new ArrayList<Object>(0);
		
		packet.setHeader(new RequestPacketHeader("send"));
		packet.setBody(new RequestPacketBody(parameters, message));
		
		crh.send(marshaller.marshall((Object) packet));
	}

	public String receive() throws IOException, InterruptedException, ClassNotFoundException {
		ClientRequestHandler crh = new ClientRequestHandler("localhost", 1313, false);
		
		Marshaller marshaller = new Marshaller();
		
		byte[] response = crh.receive();
		
		return (String) marshaller.unmarshall(response);
	}
}
