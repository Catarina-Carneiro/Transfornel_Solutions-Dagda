import requests
import json
import time
from services.mysql import Mysql
from services.pythohms import CrawlerOpenHardwareMonitor
# cria os dados para conexão
mysql = Mysql('python','bandtec', 'localhost', 'pythonms')
# conecta no banco
mysql.connect()
# 'instancia' a classe que gera os dados da maquina
crawler = CrawlerOpenHardwareMonitor()
# query para insert no banco
dataMaq = ()
dataMaq = crawler.getDevice()
insertMaq = crawler.ajusteMysql(dataMaq)
insertCpu = ("INSERT INTO `cpuComp`(maquina,core,clock,temperatura,loadCpu,horario)"
            "VALUES (%s, %s, %s, %s,%s,%s)")
# usuario digita a maquina que deseja monitorar

#cria a lista e coloca os dados dentro de listas

#insere os dados de fato
mysql.insert(dataMaq,insertMaq)
# mostra os dados inseridos
print(dataMaq)
# contador do while

# faz com que insira todos dos core da CPU da maquina



# insira apenas 10 vezes dps pede para se o usuario quer continuar
# so pra automatizar o ctrl + c
while True:
    i = 0
    # faz com que insira todos dos core da CPU da maquina
    dataCpu = ()
    dataCpu = crawler.getCPU(dataMaq[0])
    while (i < len(dataCpu)):
        mysql.insert(dataCpu[i],insertCpu)
        print(dataCpu[i])
        i = i + 1
        #faz com que demore 1 segundo para que tenha alteração substancial no
        # dados
    time.sleep(5)
    print(dataMaq)