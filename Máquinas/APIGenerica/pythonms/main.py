import requests
import json
import time
from services.mysql import Mysql
from services.pythohms import CrawlerOpenHardwareMonitor
# cria os dados para conexão
mysql = Mysql('rhian','12345', 'localhost', 'dagda')
# conecta no banco
mysql.connect()
# 'instancia' a classe que gera os dados da maquina
crawler = CrawlerOpenHardwareMonitor()
# query para insert no banco
dataMaq = ()
dataMaq = crawler.getDevice()
insertMaq = crawler.ajusteMysql(dataMaq)

# usuario digita a maquina que deseja monitorar

#cria a lista e coloca os dados dentro de listas

# contador do while
while True:
    i = 0
    # faz com que insira todos dos core da CPU da maquina
    dataCpu = []
    querySelect = crawler.isDeviceExists(dataMaq[0])
    result = mysql.select(dataMaq[i],querySelect)

    # Nessa função traz os dados do componente e da maquina para verificação antes do insert
    dadosComponente = []
    def getDataDevice(valNum):

        for row in valNum:
            dadosComponente.append({
                "idMaquina": row[0],
                "nomeMaquina": row[1],
                "idComponente": row[2],
                "tipoComponente": row[3],
                "nomeLeitura": row[4]
            })
        return dadosComponente
    
    
    componentes = []
    print(dadosComponente)
    componentes = getDataDevice(result)
    
    for componente in componentes:
        dataCpu.append(crawler.getCPU(componente))
    

    #insere os dados de fato
    # mostra os dados inseridos

    # dataCpu = crawler.getCPU(dataMaq)
    
    while (i < len(dataCpu)):
        for info in dataCpu[i]:
            mysql.insert(info,insertMaq)
            print(info)
        i = i + 1
        # faz com que demore 1 segundo para que tenha alteração substancial no
        # dados
    #Alterar de dois segundo para 5 segundos 
    time.sleep(5)
    print(dataMaq)