package jogodavelha;

import java.util.Scanner;


public class App {
	
	private char tabuleiro[][] = new char[3][3];
	private char choice;
	private int result;

	
	//getters
	public char getChoice() {
		return choice;
	}

	public int getResult() {
		return result;
	}
	
	
	//setters
	public void setChoice(char choice) {
		this.choice = choice;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
	
	//Construtor
	public App(char choice) {
		for(int i=0; i < 3; i++) {
			for(int j=0; j <3; j++) {
				tabuleiro[i][j] = '.';
			}
		}
		choice = choice;
		result = -1;
	}
	
	public String readJogada() {
		this.printTabuleiro();
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Escolha uma posição para jogar:\n1- linha (0 - 2)\n2- coluna (0 - 2)");
		int i = reader.nextInt();
		int j = reader.nextInt();
		
		jogar(i, j, true);
		checkTerminou();
		
		return "" + i + j;
	}
	
	public boolean checaFim() {
		if(getResult() != -1) {
			printResult();
		}
		
		return getResult() != -1;
	}
	
	//Trata mensagem
	public void trataMensagem(String message) {
		char r,i,j;
		
		r = message.charAt(0);
		checkResult(r);
		
		i = message.charAt(1);
		j = message.charAt(2);
		jogar(i,j,false);
	}
	
	//Modifica uma posição do tabuleiro
	public void jogar(int i, int j, boolean play) {
		if(play == true)
			tabuleiro[i][j] = getChoice();
		else
			if(getChoice() == 'O') {
				tabuleiro[i][j] = 'X';
			}
			else {
				tabuleiro[i][j] = 'O';
			}
	}
	
	// Imprime o resultado
	public void printResult() {
		if(result == 0) {
			System.out.println("Empate");
		}
		else if(result == 1) {
			System.out.println("Você ganhou");
		}
		else if(result == 2) {
			System.out.println("Você perdeu");
		}
	}
	
	//Checa o resultado da partida recebido do outro jogador
	public void checkResult(int result) {
		if(result == 0) {
			setResult(0);
		}
		else if(result == 1) {
			setResult(2);
		}
		else if(result == 2) {
			setResult(1);
		}	
	}
	
	
	//Printa o tabuleiro da partida
	public void printTabuleiro() {
		for(int i=0; i < 3; i++) {
			for(int j=0; j <3; j++) {
				System.out.print(tabuleiro[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	//Checa se o jogo terminou
	public int checkTerminou() {
		int var = 0;
		
		//O Jogador ganhou
		
		if (tabuleiro[0][0] != '.' && tabuleiro[0][1] == tabuleiro[0][2] && tabuleiro[0][1] == tabuleiro[0][0])
			setResult(1);
		
		if (tabuleiro[0][0] != '.' && tabuleiro[1][1] == tabuleiro[2][2] && tabuleiro[1][1] == tabuleiro[0][0])
			setResult(1);
		
		if (tabuleiro[0][0] != '.' && tabuleiro[1][0] == tabuleiro[2][0] && tabuleiro[1][0] == tabuleiro[0][0])
			setResult(1);
		
		if (tabuleiro[0][1] != '.' && tabuleiro[1][1] == tabuleiro[2][1] && tabuleiro[1][1] == tabuleiro[0][1])
			setResult(1);
		
		if (tabuleiro[0][2] != '.' && tabuleiro[1][2] == tabuleiro[2][2] && tabuleiro[1][2] == tabuleiro[0][2])
			setResult(1);
		
		if (tabuleiro[0][2] != '.' && tabuleiro[1][1] == tabuleiro[2][0] && tabuleiro[1][1] == tabuleiro[0][2])
			setResult(1);
		
		if (tabuleiro[1][0] != '.' && tabuleiro[1][1] == tabuleiro[1][2] && tabuleiro[1][1] == tabuleiro[1][0])
			setResult(1);
		
		if (tabuleiro[2][0] != '.' && tabuleiro[2][1] == tabuleiro[2][2] && tabuleiro[2][1] == tabuleiro[2][0])
			setResult(1);
		
		//Empate	
		for(int i=0; i<3; i++) {
			for(int j=0; j <3; j++) {
					if(tabuleiro[i][j] != '.' ) {
						var++;
					}
			}
		}
		if(var == 9) {
			setResult(0);
			checkResult(result);
		}
		return result;
	}
}
