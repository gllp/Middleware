package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import distribution.Action;
import distribution.Data;
import distribution.QueueManagerProxy;

public class App implements Runnable {
	String host;
	int port;
	
	public App(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void sendAction(String queueName, Action action) throws IOException, InterruptedException {
		QueueManagerProxy proxy = new QueueManagerProxy("localhost", 1313, queueName);
		proxy.send((Object) action);
		System.out.println("Sent action");
	}

	public void run() {
		boolean exit = false;
		DatabaseMock db = new DatabaseMock();
		Scanner reader = new Scanner(System.in);
		
		while(!exit) {
			System.out.println("Menu:\n1-Subscribe to queue\n2-See data\n3-Send action to actuator\n4-Exit");
			
			int option = reader.nextInt();
			
			switch(option) {
				case 1:
					System.out.println("Queue name:");
					String queueName = reader.next();
					QueueManagerProxy proxy = new QueueManagerProxy(host, port, queueName);
					
					try{
						proxy.subscribe();
					} catch(Exception e) {
						System.out.println("Failed to subscribe");
						e.printStackTrace();
						return;
					}
					
					break;
					
				case 2:
					System.out.println("Data type:");
					String dataType = reader.next();
					
					PrintWriter writer;
					try {
						writer = new PrintWriter("outputs.txt", "UTF-8");
					} catch (FileNotFoundException | UnsupportedEncodingException e) {
						System.out.println("Failed to write output file");
						e.printStackTrace();
						return;
					}
					
					ArrayList<Data> list = db.getData(dataType);
					for(int i = 0; i < list.size(); i++) {
						writer.println(dataType);
						ArrayList<Long> times = list.get(i).getTime();
						for(int j = 1; j < times.size(); j++) {
							writer.println(times.get(i) - times.get(i-1));
						}
					}
					break;
					
				case 3:
					System.out.println("Queue name:");
					String queue = reader.next();
					System.out.println("Action:");
					String action = reader.next();
					System.out.println("Value:");
					String value = reader.next();
					
					try {
						sendAction(queue, new Action(action, value));
					} catch (IOException | InterruptedException e) {
						System.out.println("Failed to send action");
						e.printStackTrace();
						return;
					}
					break;
				
				case 4:
					exit = true;
					break;
			}
		}
		
		reader.close();
	}
}
