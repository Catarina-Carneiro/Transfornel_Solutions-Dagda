import socket
from services.mysql import Mysql
from services.dataGenerator import getData
import time

#Inserir user, password, host, database
mysql = Mysql('rhian','12345', 'localhost', 'dagda')

mysql.connect()


while True:
    i=0
    dataCpu = []
    nome = socket.gethostname()
    result = mysql.select(nome)
    # values = getData()
    dadosComponente = [];
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
    componentes = getDataDevice(result)

    for componente in componentes:
        dataCpu.append(getData(componente))
    
    while (i < len(dataCpu)):
        for info in dataCpu[i]:
            mysql.insert(info)            
            print(info)
        i = i + 1

    # time.sleep(2)
