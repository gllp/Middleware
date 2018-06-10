package distribution;

import java.io.IOException;

import infrastructure.ServerRequestHandlerQueueManager;

public class QueueServer {
	public static void main(String[] args) throws IOException {
		ServerRequestHandlerQueueManager srh = new ServerRequestHandlerQueueManager(1313);
		
		srh.waitConnection();
	}
}
