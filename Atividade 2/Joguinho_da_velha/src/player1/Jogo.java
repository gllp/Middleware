package player1;
import java.awt.SecondaryLoop;
import java.util.Scanner;

import jogodavelha.App;
import player1.*;
import player2.Send2;

public class Jogo {
	
	public static void main(String[] args) throws Exception {		
		Scanner reader = new Scanner(System.in);
		System.out.println("Escolha um simbolo: X ou O");
		String choice = reader.next(); 
		
		Send send = new Send();
		App app = new App(choice.charAt(0));
		String msgEnviar = app.readJogada();
		try {
			send.sendMessage("" + app.getResult() + msgEnviar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//listening all the messages
		Thread receiver = new Thread(new Recv2(app));
		receiver.run();
	}
}
