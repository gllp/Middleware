package player2;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import jogodavelha.App;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Recv implements Runnable {
	private final static String QUEUE_NAME2 = "hello";
	
	
	public Send2 send2;
	public App app;
	
	public Recv(App app) {
		this.app = app;
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
			    	String message = new String(body, "UTF-8");
		    		app.trataMensagem(message);
		    		if(app.checaFim()) {
		    			return;
		    		}
		    		
		    		String msgEnviar = app.readJogada();
		    		try {
						send2.sendMessage("" + app.getResult() + msgEnviar);
					} catch (Exception e) {
						e.printStackTrace();
					}
		    		
		    		app.checaFim();
	          	}
		    };
		    channel.basicConsume(QUEUE_NAME2, true, consumer); 
		}catch(IOException | TimeoutException e) {
			e.printStackTrace();
			
		}
	}
}