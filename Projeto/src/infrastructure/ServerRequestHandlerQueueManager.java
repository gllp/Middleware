package infrastructure;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import distribution.QueueManager;

public class ServerRequestHandlerQueueManager {
	private int port;
	private int receiveMessageSize;
	
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	
	private DataInputStream inFromClient;
	
	public ServerRequestHandlerQueueManager(int port) {
		this.port = port;
	}
	
	public void waitConnection() throws IOException {
		serverSocket = new ServerSocket(port);
		
		while(true) {
			System.out.println("Waiting connection");
			connectionSocket = serverSocket.accept();
			
			inFromClient = new DataInputStream(connectionSocket.getInputStream());
			
			receiveMessageSize = inFromClient.readInt();
			
			byte[] data = new byte[receiveMessageSize];
			inFromClient.readFully(data, 0, receiveMessageSize);
			
			new Thread(new QueueManager(
				connectionSocket.getInetAddress().getHostAddress(),
				data)).start();
			
			inFromClient.close();
			connectionSocket.close();
		}
	}
}
