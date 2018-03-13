import socket
import os

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

host = ''
port = 1235

variavel = (host, port)

print("O que preferes:\n 1- Esperar Conexao\n 2- Conectar\n")

x = 0

#Estabelecendo a conexao entre os clients

while ((x != 1) and (x != 2)):
	x = int(raw_input())

if x == 1:
	try:
		s.bind( variavel )
	except socket.error as e:
		print (e)
		exit(0)
	
	s.listen(1)
	conn, adr = s.accept()
	
else:
	try:
		s.connect( variavel )
	except socket.error as e:
		print(e)
		exit(0)	
	conn = s

#Iniciando o jogo
	
print("Bem-Vindo ao Jogo da Velha!!!\n")

Tabuleiro = [['.','.','.'],['.','.','.'],['.','.','.']]

jog1 = 0
jog2 = 0
turno = 0
fim = 0

#Escolhendo o simbolo para jogar

if x == 1:
	print("Escolha se voce quer jogar com:\n 1- X\n 2- O:")
	while (jog1 != 1 and jog1 != 2):
		jog1 = int(raw_input())
	if jog1 == 1:
		print("Seras X :D !!")
	else:
		print("Seras O :D!!")
	data = str(jog1)
	conn.send(data.encode())

if x == 2:
	print("Espere o jogador 1 escolher!")
	data = conn.recv(1024)
	opc = int(data)
	
	if opc == 1:
		jog2 = 2
		print("Voce sera O!!")
	else:
		jog2 = 1
		print("Voce sera X!!")

os.system('clear')

print("Vai comecar o jogo!!\n")

while fim == 0:
	
	os.system('clear')
	
	for i in [0,1,2]:
		print Tabuleiro[i][0], Tabuleiro[i][1], Tabuleiro[i][2]
		
	#Jogada do jogador 1
	
	if (turno % 2) == 0:
		print("Vez do jogador 1")
		if (x == 1):
			
			print("Qual a posicao da jogada (ambas as coordenadas entre 0 e 2):")
			a = b = -1
			posinv = 0
			
			while ((a < 0 or a > 2) or (b < 0 or b > 2) or posinv == 0):
				
				print("Digite a linha")
				a = int(raw_input())
				
				print("Digite a coluna")
				b = int(raw_input())
				
				if ((a < 0 or a > 2) or (b < 0 or b > 2)):
					os.system('clear')
					print("Jogada invalida, tente de novo\n")
					for i in [0,1,2]:
						print Tabuleiro[i][0], Tabuleiro[i][1], Tabuleiro[i][2]
					
				
				elif (Tabuleiro[a][b] != '.'):
					os.system('clear')
					print("Posicao ja ocupada, tente de novo\n")
					for i in [0,1,2]:
						print Tabuleiro[i][0], Tabuleiro[i][1], Tabuleiro[i][2]
				
				else:
					posinv = 1
			
			if (jog1 == 1):
				Tabuleiro[a][b] = 'X'
				
			else:
				Tabuleiro[a][b] = 'O'
				
			
			data = str(a) + ' ' + str(b)
			conn.send(data.encode())
		
		if (x == 2):
			os.system('clear')
			print("Aguarde o jogador 1")
			
			for i in [0,1,2]:
				print Tabuleiro[i][0], Tabuleiro[i][1], Tabuleiro[i][2]
			
			data = conn.recv(1024)
			
			strk = data.split()
			
			a = int(strk[0])
			b = int(strk[1])
			
			if jog2 == 1:
				Tabuleiro[a][b] = 'O'
				
			else:
				Tabuleiro[a][b] = 'X'
	
	#Jogada do jogador 2
						
	else:
		
		print("Vez do jogador 2")
		if (x == 2):
			
			print("Qual a posicao da jogada (ambas as coordenadas entre 0 e 2):")
			a = b = -1
			posinv = 0
			
			while ((a < 0 or a > 2) or (b < 0 or b > 2) or posinv == 0):
				
				print("Digite a linha")
				a = int(raw_input())
				
				print("Digite a coluna")
				b = int(raw_input())
				
				if ((a < 0 or a > 2) or (b < 0 or b > 2)):
					os.system('clear')
					print("Jogada invalida, tente de novo\n")
					for i in [0,1,2]:
						print Tabuleiro[i][0], Tabuleiro[i][1], Tabuleiro[i][2]
				
				elif (Tabuleiro[a][b] != '.'):
					os.system('clear')
					print("Posicao ja ocupada, tente de novo\n")
					for i in [0,1,2]:
						print Tabuleiro[i][0], Tabuleiro[i][1], Tabuleiro[i][2]
				
				else:
					posinv = 1
			
			if (jog2 == 1):
				Tabuleiro[a][b] = 'X'
				
			else:
				Tabuleiro[a][b] = 'O'
				
			
			data = str(a) + ' ' + str(b)
			conn.send(data.encode())
		
		if (x == 1):
			os.system('clear')
			print("Aguarde o jogador 2")
			
			for i in [0,1,2]:
				print Tabuleiro[i][0], Tabuleiro[i][1], Tabuleiro[i][2]
			
			data = conn.recv(1024)
			
			strk = data.split()
			
			a = int(strk[0])
			b = int(strk[1])
			
			if jog1 == 1:
				Tabuleiro[a][b] = 'O'
				
			else:
				Tabuleiro[a][b] = 'X'
	
	#Verificacao de se alguem ganhou
	
	if (Tabuleiro[0][0] != '.' and Tabuleiro[0][1] == Tabuleiro[0][2] and Tabuleiro[0][1] == Tabuleiro[0][0]):
		fim = 1
	
	if (Tabuleiro[0][0] != '.' and Tabuleiro[1][1] == Tabuleiro[2][2] and Tabuleiro[1][1] == Tabuleiro[0][0]):
		fim = 1
	
	if (Tabuleiro[0][0] != '.' and Tabuleiro[1][0] == Tabuleiro[2][0] and Tabuleiro[1][0] == Tabuleiro[0][0]):
		fim = 1
	
	if (Tabuleiro[0][1] != '.' and Tabuleiro[1][1] == Tabuleiro[2][1] and Tabuleiro[1][1] == Tabuleiro[0][1]):
		fim = 1
	
	if (Tabuleiro[0][2] != '.' and Tabuleiro[1][2] == Tabuleiro[2][2] and Tabuleiro[1][2] == Tabuleiro[0][2]):
		fim = 1
	
	if (Tabuleiro[0][2] != '.' and Tabuleiro[1][1] == Tabuleiro[2][0] and Tabuleiro[1][1] == Tabuleiro[0][2]):
		fim = 1
	
	if (Tabuleiro[1][0] != '.' and Tabuleiro[1][1] == Tabuleiro[1][2] and Tabuleiro[1][1] == Tabuleiro[1][0]):
		fim = 1
	
	if (Tabuleiro[2][0] != '.' and Tabuleiro[2][1] == Tabuleiro[2][2] and Tabuleiro[2][1] == Tabuleiro[2][0]):
		fim = 1
	
	cont = 0
	
	#Verificacao de empate	
	
	if (fim == 0):
		for i in [0,1,2]:
			for j in range(0,3):
				if (Tabuleiro[i][j] != '.'):
					cont += 1						
	
	if (fim == 1):
		print("\n")
		os.system('clear')
		
		for i in [0,1,2]:
			print Tabuleiro[i][0], Tabuleiro[i][1], Tabuleiro[i][2]
		
		if((turno % 2) == 0):
			print("Jogador 1 Ganhou!!!")
	
		else:
			print("Jogador 2 Ganhou!!!")
	
	if (cont == 9):
		os.system('clear')
		
		for i in [0,1,2]:
			print Tabuleiro[i][0], Tabuleiro[i][1], Tabuleiro[i][2]
		
		fim = 1
		print ("Terminou em empate!!!")		
	
	turno = (turno + 1) % 2
	
#Fechando a conexao	

if x == 1:
	conn.close();
	s.close();

else:
	s.close();
