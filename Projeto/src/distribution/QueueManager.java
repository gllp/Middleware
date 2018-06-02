package distribution;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QueueManager{
	private String host;
	private int port;
	Map<String, Queue> queues = new HashMap<String, Queue>();
	
	public QueueManager(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void send(Message msg, String queueName) throws IOException, InterruptedException{
		queues.get(queueName).enqueue(msg);
	}
	
	
	public Message receive(String queueName) throws IOException, InterruptedException, ClassNotFoundException{
		return queues.get(queueName).dequeue();
		
	}
}
