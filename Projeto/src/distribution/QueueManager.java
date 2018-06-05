package distribution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QueueManager implements Runnable {
	private String host;
	private int port;
	private int connId;
	private byte[] data;
	static Map<String, Queue> queues = new HashMap<String, Queue>();
	static Map<Address, ArrayList<String>> subscriptions = new HashMap<Address, ArrayList<String>>();
	
	public QueueManager(String host, int port, int connId, byte[] data) {
		this.host = host;
		this.port = port;
		this.connId = connId;
		this.data = data;
	}

	public void publish(Message msg) throws IOException, InterruptedException{
		String queueName = msg.getHeader().getDestination();
		
		if(queues.get(queueName) == null) {
			queues.put(queueName, new Queue());
		}
		
		queues.get(queueName).enqueue(msg);
	}
	
	public void subscribe(Message msg) {
		Address address = new Address(this.host, this.port);
		
		if(subscriptions.get(address) == null) {
			subscriptions.put(address,  new ArrayList<String>());
		}
		
		subscriptions.get(address).add(msg.getHeader().getDestination());
	}
	
	public void run() {
		Marshaller marshaller = new Marshaller();
		RequestPacket msgUnmarshalled;
		try {
			msgUnmarshalled = (RequestPacket) marshaller.unmarshall(this.data);
		} catch(Exception e) {
			System.out.println("Unmarshalling failed");
			e.printStackTrace();
			return;
		}
		
		switch(msgUnmarshalled.getHeader().getOperation()) {
			case "publish":
				try {
					this.publish(msgUnmarshalled.getBody().getMessage());
				} catch(Exception e) {
					System.out.println("Publish failed");
					e.printStackTrace();
				}
				
				break;
			case "subscribe":
				this.subscribe(msgUnmarshalled.getBody().getMessage());
				break;
		}
	}
}
