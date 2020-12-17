Olá, aqui explico como você ira rodar o ShellScript.

1-Passo para executar o seu shell script:

a. Altere a permissão do arquivo para que fique executavel:

	chmod +x ShellCompleto

b. Se você já tem seu Container criado basta alterar no Script 
o Id do seu container exemplo:

	sudo docker start IDdoseuContainer123 
	sudo docker exec -it IDdoseuContainer123  bash	

c. Digite no prompt de comando, no diretorio em que o 
shell se encontra:

	./ShellCompleto

Então ira executar o Script

2-Passo ele ira te levar para o Conteiner do Docker 
então você tera que executar apenas um comando para sair

	exit

3-Passo então ira abrir o seu executavel jar caso já tenha 
instalado todos os pacotes, se não ele ira instalar e logo 
em seguida seu jar será executavel.

							:)