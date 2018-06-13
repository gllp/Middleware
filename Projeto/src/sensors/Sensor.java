package sensors;

import java.io.IOException;
import java.util.Random;

import distribution.Data;
import distribution.QueueManagerProxy;

public class Sensor implements Runnable {
	String queueName;
	
	public Sensor(String queueName) {
		this.queueName = queueName;
	}
	
	public void run() {
		QueueManagerProxy proxy = new QueueManagerProxy("localhost", 1313, queueName);
		Random generator = new Random();
		
		while(true) {
			try {
				proxy.send((Object) new Data(queueName, String.valueOf(generator.nextDouble())));
			} catch (IOException | InterruptedException e) {
				System.out.println("Failed to publish data on " + queueName);
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.out.println("Failed to sleep on sensor " + queueName);
				e.printStackTrace();
			}
		}
	}
}
