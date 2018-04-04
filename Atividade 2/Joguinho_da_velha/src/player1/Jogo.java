package player1;
import java.awt.SecondaryLoop;
import java.util.Scanner;

import jogodavelha.App;
import player1.*;

public class Jogo {
	
	public static void main(String[] args) throws Exception {		
		Scanner reader = new Scanner(System.in);
		System.out.println("Escolha um simbolo: X ou O");
		String choice = reader.next(); 
		
		App app = new App(choice.charAt(0));
		app.readJogada();
		
		//listening all the messages
		Thread receiver = new Thread(new Recv2(app));
		receiver.run();
	}
}
