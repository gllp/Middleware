package player1;
import java.awt.SecondaryLoop;

import player1.*;

public class Jogo {
	public boolean messagereceived;
	public static String message;
	
	//handle delivery
	public static void main(String[] args) throws Exception {
		Thread receiver = new Thread(new Recv2());
		Send send = new Send();
		
		//listening all the messages
		receiver.run();

		//send a message
		message = "oi";
		send.sendMessage(message);
		
	}
	
	public void testMessage() {
		System.out.println(message);
	}
	
}
