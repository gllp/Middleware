package calculator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRequestHandler {
	private int port;
	private int sentMessageSize;
	private int receiveMessageSize;
	
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	
	private DataOutputStream outToClient;
	private DataInputStream inFromClient;
	
	public ServerRequestHandler(int port) {
		this.port = port;
	}
	
	public void send(byte[] msg) throws IOException {
		sentMessageSize = msg.length;
		
		outToClient.writeInt(sentMessageSize);
		outToClient.write(msg, 0, sentMessageSize);
		
		connectionSocket.close();
		serverSocket.close();
		inFromClient.close();
		outToClient.close();
		
		System.out.println("Fechou server socker");
	} 
	
	public byte[] receive() throws IOException {
		serverSocket = new ServerSocket(port);
		connectionSocket = serverSocket.accept();
		
		inFromClient = new DataInputStream(connectionSocket.getInputStream());
		outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		
		receiveMessageSize = inFromClient.readInt();
		
		byte[] data = new byte[receiveMessageSize];
		inFromClient.readFully(data, 0, receiveMessageSize);
		
		return data;
	}
}
