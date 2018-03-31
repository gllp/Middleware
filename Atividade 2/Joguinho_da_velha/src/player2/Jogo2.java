package player2;

import player2.*;

public class Jogo2 {
		public boolean messagereceived;
		public static String message;
		
		
		//handle delivery
		public static void main(String[] args) throws Exception {
			Thread receiver = new Thread(new Recv());
			Send2 send = new Send2();
			
			//listening all the messages
			receiver.run();

			//send a message
			message = "oi";
			//send.sendMessage(message);
			
		}
		
		public void testMessage() {
			System.out.println(message);
		}
		
	}

