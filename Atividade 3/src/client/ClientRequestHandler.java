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
	
	public ClientRequestHandler(String host, int port) throws IOException {
		this.host = host;
		this.port = port;
		clientSocket = new Socket(this.host, this.port);
		
		this.inFromServer = new DataInputStream(this.clientSocket.getInputStream());
		this.outToServer = new DataOutputStream(this.clientSocket.getOutputStream());
	}
	
	public void send(byte[] msg) throws IOException {
		sentMessageSize = msg.length;
		
		outToServer.writeInt(sentMessageSize);
		outToServer.write(msg, 0, sentMessageSize);
	} 
	
	public byte[] receive() throws IOException {
		receiveMessageSize = inFromServer.readInt();
		
		byte[] data = new byte[receiveMessageSize];
		inFromServer.readFully(data, 0, receiveMessageSize);
		
		return data;
	}
}
