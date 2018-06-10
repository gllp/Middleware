package application;

import java.util.Scanner;

import distribution.QueueManagerProxy;

public class App implements Runnable {
	String host;
	int port;
	
	public App(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() {
		boolean exit = false;
		DatabaseMock db = new DatabaseMock();
		Scanner reader = new Scanner(System.in);
		
		while(!exit) {
			System.out.println("Menu:\n1-Subscribe to queue\n2-See data\n3-Exit");
			
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
					System.out.println(db.getData());
					break;
				
				case 3:
					exit = true;
					break;
			}
		}
		
		reader.close();
	}
}
