package player2;

import java.util.Scanner;

import jogodavelha.App;
import player1.Recv2;
import player2.*;

public class Jogo2 {	
	public static void main(String[] args) throws Exception {
		Scanner reader = new Scanner(System.in);
		System.out.println("Escolha um simbolo: X ou O");
		String choice = reader.next(); 
		
		App app = new App(choice.charAt(0));
		
		//listening all the messages
		Thread receiver = new Thread(new Recv(app));
		receiver.run();
		
	}
}

