package actuatortemp;

import distribution.Action;
import distribution.Message;
import distribution.QueueManagerProxy;

public class Receiver implements Runnable {
	int port;
	
	public Receiver(int port) {
		this.port = port;
	}
	
	public void performAction(Action action) {
		if(action.getAction().equals("increase")) {
			//increase temperature by Double.parseDouble(action.getValue())
			System.out.println("Temperature increased by " + action.getValue());
		}
		else {
			//decrease temperature by Double.parseDouble(action.getValue())
			System.out.println("Temperature decreased by " + action.getValue());
		}
	}

	public void run() {
		QueueManagerProxy proxy = new QueueManagerProxy(null, port, null); 
		
		Message msgUnmarshalled = null;
		
		while(true) {
			try {
				msgUnmarshalled = (Message) proxy.receive();
			} catch(Exception e) {
				System.out.println("Failed to receive message");
				e.printStackTrace();
				return;
			}
			
			performAction((Action) msgUnmarshalled.getBody().getBody());
		}
	}
}

