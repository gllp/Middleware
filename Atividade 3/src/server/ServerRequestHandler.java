package server;

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
	
	public ServerRequestHandler(int port) throws IOException {
		this.port = port;
		this.serverSocket = new ServerSocket(this.port);
		this.connectionSocket = this.serverSocket.accept();
		
		this.inFromClient = new DataInputStream(this.connectionSocket.getInputStream());
		this.outToClient = new DataOutputStream(this.connectionSocket.getOutputStream());
	}
	
	public void send(byte[] msg) throws IOException {
		sentMessageSize = msg.length;
		
		outToClient.writeInt(sentMessageSize);
		outToClient.write(msg, 0, sentMessageSize);
	} 
	
	public byte[] receive() throws IOException {
		receiveMessageSize = inFromClient.readInt();
		
		byte[] data = new byte[receiveMessageSize];
		inFromClient.readFully(data, 0, receiveMessageSize);
		
		return data;
	}
}
