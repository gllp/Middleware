package player2;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;


public class Send2 {
	private final static String QUEUE_NAME = "hello2";
	
	public static void sendMessage(String message) throws Exception {
		
		//create connection
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		//create channel
		Channel channel = connection.createChannel();
		//send message to queue
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println("[x] Sent '" + message + "'");
		
		
		channel.close();
		connection.close();
		
	}

}
