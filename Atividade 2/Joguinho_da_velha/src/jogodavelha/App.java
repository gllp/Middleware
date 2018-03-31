package jogodavelha;


public class App {
	
	private char tabuleiro[][] = new char[3][3];
	private char choice;
	private int result;
	private String jogador;
	private String message;

	
	//getters
	public char getChoice() {
		return choice;
	}

	public int getResult() {
		return result;
	}
	public String getMessage() {
		return message;
	}
	
	
	//setters
	public void setChoice(char choice) {
		this.choice = choice;
	}

	public void setResult(int result) {
		this.result = result;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	//Construtor
	public App(char choice, String jogador) {
		for(int i=0; i < 3; i++) {
			for(int j=0; j <3; j++) {
				tabuleiro[i][j] = '.';
			}
		}
		this.choice = choice;
		this.jogador = jogador;
		result = -1;
	}
	
	//Trata mensagem
	public void trataMensagem(boolean play) {
		char primeiroChar,i,j;
		
		primeiroChar = getMessage().charAt(0);
		setResult(primeiroChar);
		
		i = getMessage().charAt(1);
		j = getMessage().charAt(2);
		Jogar(i,j,false);
	}
	
	//Modifica uma posição do tabuleiro
	public void Jogar(int i, int j, boolean play) {
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
	
	//Checa o resultado da partida
	public void checkResult(int result) {
		if(getResult() == 0) {
			System.out.println("Empate");
			setResult(0);
		}
		else if(getResult() == 1) {
			System.out.println("Você ganhou");
			setResult(2);
		}
		else if(getResult() == 2) {
			System.out.println("Você perdeu");
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
	public int Terminou(char tabuleiro[][]) {
		int var = 0;
		
		//O Jogador ganhou
		
		if (tabuleiro[0][0] != '.' && tabuleiro[0][1] == tabuleiro[0][2] && tabuleiro[0][1] == tabuleiro[0][0])
			setResult(1);
			checkResult(result);
		
		if (tabuleiro[0][0] != '.' && tabuleiro[1][1] == tabuleiro[2][2] && tabuleiro[1][1] == tabuleiro[0][0])
			setResult(1);
			checkResult(result);
		
		if (tabuleiro[0][0] != '.' && tabuleiro[1][0] == tabuleiro[2][0] && tabuleiro[1][0] == tabuleiro[0][0])
			setResult(1);
			checkResult(result);
		
		if (tabuleiro[0][1] != '.' && tabuleiro[1][1] == tabuleiro[2][1] && tabuleiro[1][1] == tabuleiro[0][1])
			setResult(1);
			checkResult(result);
		
		if (tabuleiro[0][2] != '.' && tabuleiro[1][2] == tabuleiro[2][2] && tabuleiro[1][2] == tabuleiro[0][2])
			setResult(1);
			checkResult(result);
		
		if (tabuleiro[0][2] != '.' && tabuleiro[1][1] == tabuleiro[2][0] && tabuleiro[1][1] == tabuleiro[0][2])
			setResult(1);
			checkResult(result);
		
		if (tabuleiro[1][0] != '.' && tabuleiro[1][1] == tabuleiro[1][2] && tabuleiro[1][1] == tabuleiro[1][0])
			setResult(1);
			checkResult(result);
		
		if (tabuleiro[2][0] != '.' && tabuleiro[2][1] == tabuleiro[2][2] && tabuleiro[2][1] == tabuleiro[2][0])
			setResult(1);
			checkResult(result);
		
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
