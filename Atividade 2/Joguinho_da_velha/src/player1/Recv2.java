package player1;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Recv2 implements Runnable {
	private final static String QUEUE_NAME2 = "hello2";
	
	public String message;
	public boolean messageReceived;
	public Send send;
	
	public void sendBack(String message) throws Exception {
		send.sendMessage(message+"a");
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isMessageReceived() {
		return messageReceived;
	}

	public void setMessageReceived(boolean messageReceived) {
		this.messageReceived = messageReceived;
	}

	@Override
	public void run(){
		// TODO Auto-generated method stub
		try {
			ConnectionFactory factory = new ConnectionFactory();
		    factory.setHost("localhost");
		    Connection connection = factory.newConnection();
		    Channel channel = connection.createChannel();
		    channel.queueDeclare(QUEUE_NAME2, false, false, false, null);
		   
		    
		    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		
		    Consumer consumer = new DefaultConsumer(channel) {	     
		    @Override
		      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
		              throws IOException {
		    		setMessage(new String(body, "UTF-8"));
		    		setMessageReceived(true);
		    		
		    		if(isMessageReceived() == true) {
			    		try {
							sendBack(message);
						} catch (Exception e) {
							e.printStackTrace();
						}
			    		setMessageReceived(false);
			    		
		    		}
		    		
		            System.out.println(" Player 1 - Received Message '" + message + "'");
		           
		            
		          }
		    };
		    channel.basicConsume(QUEUE_NAME2, true, consumer); 
		}catch(IOException | TimeoutException e) {
			e.printStackTrace();
			
		}
	}
}