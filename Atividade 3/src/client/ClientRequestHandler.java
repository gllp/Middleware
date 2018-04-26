package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientRequestHandler {
	private String host;
	private int port;
	private int sentMessageSize;
	private int receiveMessageSize;
	
	private Socket clientSocket;
	
	private DataOutputStream outToServer;
	private DataInputStream inFromServer;
	
	public ClientRequestHandler(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void send(byte[] msg) throws IOException {
		sentMessageSize = msg.length;
		
		clientSocket = new Socket(host, port);
		
		inFromServer = new DataInputStream(clientSocket.getInputStream());
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
		
		outToServer.writeInt(sentMessageSize);
		outToServer.write(msg, 0, sentMessageSize);
	} 
	
	public byte[] receive() throws IOException {
		receiveMessageSize = inFromServer.readInt();
		
		byte[] data = new byte[receiveMessageSize];
		inFromServer.readFully(data, 0, receiveMessageSize);
		
		clientSocket.close();
		inFromServer.close();
		outToServer.close();
		
		System.out.println("Fechou client socker");
		
		return data;
	}
}
