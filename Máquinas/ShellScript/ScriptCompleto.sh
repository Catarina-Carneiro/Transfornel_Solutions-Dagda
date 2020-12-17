#!/bin/bash

sudo docker start a622dffc3ea8 
sudo docker exec -it a622dffc3ea8 bash

which java | grep -q /bin/java
if [[ $? = 0 ]]
then  
        echo "Realizando execução do Java"
        if [[ -d "$/home/a02201009/Desktop/JarIMC" ]]
        then 
                echo "Esse diretorio existe"
        else         
                cd /home/a02201009/Desktop
                git clone https://github.com/Catarina-Carneiro/JarIMC.git
                cd /home/a02201009/Desktop/JarIMC
                java -jar Continua3-1.0-SNAPSHOT-jar-with-dependencies.jar 
        fi
        cd /home/a02201009/Desktop/JarIMC
        java -jar Continua3-1.0-SNAPSHOT-jar-with-dependencies.jar 
else    
        echo "Realiazndo instalação do Java"
        sudo apt install curl
        sudo apt install curl
        curl -s “https://get.sdkman.io” | bash
        source “$HOME/.sdkman/bin/sdkman-init.sh”
        sdk install java 8.0.265.j9-adpt
        echo "Realizando execução do Java"
	if [[ -d "$/home/a02201009/Desktop/JarIMC" ]]
	then
		echo"Essse diretorio existe"
	else
        cd /home/a02201009/Desktop/JarIMC
        git clone https://github.com/Catarina-Carneiro/JarIMC.git
        java -jar Continua3-1.0-SNAPSHOT-jar-with-dependencies.jar
	fi
	 cd /home/a02201009/Desktop/JarIMC
        java -jar Continua3-1.0-SNAPSHOT-jar-with-dependencies.jar 
fi
