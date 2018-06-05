package infrastructure;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import distribution.QueueManager;

public class ServerRequestHandler {
	private int port;
	private int sentMessageSize;
	private int receiveMessageSize;
	
	private ServerSocket serverSocket;
	private List<Socket> connectionSockets = new ArrayList<>();
	
	private List<DataOutputStream> outToClient = new ArrayList<>();
	private List<DataInputStream> inFromClient = new ArrayList<>();
	
	public ServerRequestHandler(int port) throws IOException {
		this.port = port;
		this.serverSocket = new ServerSocket(this.port);
	}
	
	public void send(byte[] msg, int connId) throws IOException {
		sentMessageSize = msg.length;
		
		outToClient.get(connId).writeInt(sentMessageSize);
		outToClient.get(connId).write(msg, 0, sentMessageSize);
		
		connectionSockets.get(connId).close();
		inFromClient.get(connId).close();
		outToClient.get(connId).close();
		
		System.out.println("Closed connection " + connId);
	} 
	
	public void waitConnection() throws IOException {
		while(true) {
			connectionSockets.add(serverSocket.accept());
			
			int connId = connectionSockets.size() - 1;
			
			inFromClient.add(new DataInputStream(connectionSockets.get(connId).getInputStream()));
			outToClient.add(new DataOutputStream(connectionSockets.get(connId).getOutputStream()));
			
			receiveMessageSize = inFromClient.get(connId).readInt();
			
			byte[] data = new byte[receiveMessageSize];
			inFromClient.get(connId).readFully(data, 0, receiveMessageSize);
			
			new Thread(new QueueManager(
				connectionSockets.get(connId).getRemoteSocketAddress().toString(), 
				connectionSockets.get(connId).getPort(), 
				connId, 
				data)).start();
		}
	}
}
