package infrastructure;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRequestHandler {
	private int port;
	private int receiveMessageSize;
	
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	
	private DataInputStream inFromClient;
	
	public ServerRequestHandler(int port) {
		this.port = port;
	}
	
	public void closeConnection() throws IOException {
		inFromClient.close();
		connectionSocket.close();
		serverSocket.close();
	}
	
	public byte[] receive() throws IOException {
		serverSocket = new ServerSocket(port);
		connectionSocket = serverSocket.accept();
		
		inFromClient = new DataInputStream(connectionSocket.getInputStream());
		
		receiveMessageSize = inFromClient.readInt();
		
		byte[] data = new byte[receiveMessageSize];
		inFromClient.readFully(data, 0, receiveMessageSize);
		
		return data;
	}
}
