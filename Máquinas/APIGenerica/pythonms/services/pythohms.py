import requests
import json
import time
import operator
import functools
from datetime import datetime



class CrawlerOpenHardwareMonitor:
    def __init__(self):
        # requisição para o json
        self.url = 'http://localhost:9000/data.json'
        self.data = None
    
    def getJsonData(self):
        # aplica o metodo get na url, para trazer os dados
        response = requests.get(self.url)
        
        # os dados são transformados em json
        data = response.json()

        # o self.data recebe o json
        self.data = data

    # pega os dados da maquina

    def getDevice(self):
        self.getJsonData()

        AllDevices = []
        
        data = self.data

        for i in data['Children']:
            AllDevices.append(i['Text'])

        return AllDevices

    def isDeviceExists(self,nomeMaquina):
        query = (f"select ma.idMaquina, ma.nome, mc.idMaqComp, co.tipoComponente, mc.nome from Maquina as ma inner join MaqComp as mc on mc.fkMaquina = ma.idMaquina inner join Componente as co on mc.fkComponente = co.idComponente where ma.nome = '{nomeMaquina}'")
        return query

    def ajusteMysql(self, lista):
        query = ''
        if len(lista) > 0:
            query = ("INSERT INTO LEITURAS(leitura,horaDeLeitura,fkMaqComp) VALUES(%s,%s,%s)")
        return query
    # pega apenas os dados da CPU

    

    def getCPU(self,dadosParaLeitura):
        self.getJsonData()
        componentes = dadosParaLeitura
        
        
        # Ajusta os componenteses de string para decimal antes de ir para o banco
        def ajuste(param):
            param = str(param)
            param = param.split(" ")[0]
            return param.replace(",",".")

        # Função que faz uma media com os clocks da maquina
        somaClocks = 0
        contador = 0
        def getMediaValores(mediaValores):
            contador = len(mediaValores)
            somaClocks = functools.reduce(operator.add,mediaValores)           
            media = somaClocks / contador
            return media

        def getLoadCpu(cpu_metrics):
            if cpu_metrics['Text'] == 'Load':
                for load in cpu_metrics['Children']:
                    if load['Text'].find('CPU Total') >= 0:
                        leitura.append(load['Value'])

        def getClocks(cpu_metrics):
            if cpu_metrics['Text'] == 'Clocks':
                for clock in cpu_metrics['Children']:
                    if clock['Text'].find('CPU') >= 0:
                        clocks.append(clock['Value'])

        def getTemperature(cpu_metrics):
            if cpu_metrics['Text'] == 'Temperatures':
                for temps in cpu_metrics['Children']:
                    if temps['Text'].find('CPU Package') >= 0:
                        leitura.append(temps['Value'])


        def getVerificaComponente(cpu_metrics):
            if componentes['tipoComponente'] == "cpu":
                if componentes['nomeLeitura'] == "uso":
                    getLoadCpu(cpu_metrics)
                elif componentes['nomeLeitura'] == "clock":
                    getClocks(cpu_metrics) 
                elif componentes['nomeLeitura'] == "temperatura":
                    getTemperature(cpu_metrics)

                    
        CPU = []
        leitura = []
        data = self.data
        clocks = []
        clockPosAjuste = []
        #CPU
        #Aqui é buscado os dados da máquina
        for i in data['Children']:
            for desktop in i['Children']:
                if desktop['Text'].find('Intel') >= 0 or desktop['Text'].find('AMD') >= 0:
                    for cpu_metrics in desktop['Children']:
                        getVerificaComponente(cpu_metrics)

        for index,item in enumerate(clocks):
            clockPosAjuste.append(float(ajuste(clocks[index])))
            
        if(len(clockPosAjuste) > 0):
            leitura.append(getMediaValores(clockPosAjuste))
        
        for index,item in enumerate(leitura):
            data_e_hora_atuais = datetime.now()
            horario = data_e_hora_atuais.strftime('%Y-%m-%d %H:%M:%S')
        
            cpu = (
                ajuste(leitura[index]),
                horario,
                componentes["idComponente"]
            )
            
            CPU.append(cpu)
            
        return CPU
