package distribution;

import infrastructure.ClientRequestHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QueueManager implements Runnable {
	private String host;
	private int port;
	private byte[] data;
	private static final Object queuesLock = new Object();
	private static final Object subscriptionsLock = new Object();
	static Map<String, Queue> queues = new HashMap<String, Queue>();
	static Map<String, ArrayList<Address>> subscriptions = new HashMap<String, ArrayList<Address>>();
	
	public QueueManager(String host, int port, byte[] data) {
		this.host = host;
		this.port = port;
		this.data = data;
	}

	public synchronized void publish(Message msg) throws IOException, InterruptedException{
		String queueName = msg.getHeader().getDestination();
		
		synchronized(queuesLock) {
			if(queues.get(queueName) == null) {
				queues.put(queueName, new Queue());
			}
			
			queues.get(queueName).enqueue(msg);
			
			Marshaller marshaller = new Marshaller();
			
			synchronized(subscriptionsLock) {
				ArrayList<Address> addresses = subscriptions.get(queueName);
				for(int i = 0; i < addresses.size(); i++){
					ClientRequestHandler crh = new ClientRequestHandler(addresses.get(i).getIp(), addresses.get(i).getPort(), false);
					
					crh.send(marshaller.marshall(msg));
				}
			}
			queues.get(queueName).dequeue();
		}
	}
	
	public synchronized void subscribe(Message msg) {
		Address address = new Address(this.host, this.port);
		String queueName = msg.getHeader().getDestination();
		
		synchronized(subscriptionsLock) {
			if(subscriptions.get(queueName) == null) {
				System.out.println("Queue doesn't exist");
				return;
			}
		
			if(!subscriptions.get(queueName).contains(address)) {
				subscriptions.get(queueName).add(address);			
			}
		}
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
					return;
				}
				
				break;
			case "subscribe":
				this.subscribe(msgUnmarshalled.getBody().getMessage());
				break;
		}
	}
}
