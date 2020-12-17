import requests
import json
import time
from datetime import datetime




class CrawlerOpenHardwareMonitor:
    
    def __init__(self):
        self.url = 'http://localhost:9000/data.json'
        self.data = None
    
    def getJsonData(self):
        response = requests.get(self.url)
        data = json_data = response.json()
        self.data = data
# pega os dados da maquina
    def getDevice(self):
        self.getJsonData()
        AllDevices = []
        
        data = self.data

        for i in data['Children']:
            for desktop in i['Children']:
                if desktop['Text'].find('Generic Hard Disk') < 0:
                     AllDevices.append(desktop['Text'])
        return AllDevices
    def ajusteMysql(self, lista):
        if len(lista) > 5:
            query = ("INSERT INTO `descMaq`(motherBoard,processador,generico,placa,HD)"
            "VALUES (%s, %s, %s,%s, %s)")
        else:
            query = ("INSERT INTO `descMaq`(motherBoard,processador,generico,HD)"
            "VALUES (%s, %s, %s,%s)")
        return query
    # pega apenas os dados da CPU
    def getCPU(self,nome):
        self.getJsonData()

        CPU = []
        clocks = []
        temperatures = []
        loads = []
        data = self.data

                #CPU
        for i in data['Children']:
            for desktop in i['Children']:
                if desktop['Text'].find('Intel') >= 0 or desktop['Text'].find('AMD') >= 0:
                    for cpu_metrics in desktop['Children']:
                        #clock
                        if cpu_metrics['Text'] == 'Clocks':
                            for clock in cpu_metrics['Children']:
                                if clock['Text'].find('CPU') >= 0:
                                    clocks.append(clock['Value'])
                                #temperature
                        if cpu_metrics['Text'] == 'Temperatures':
                            for temps in cpu_metrics['Children']:
                                if temps['Text'].find('CPU') >= 0:
                                    temperatures.append(temps['Value'])
                                    #load
                        if cpu_metrics['Text'] == 'Load':
                            for load in cpu_metrics['Children']:
                                if load['Text'].find('CPU') >= 0:
                                    loads.append(load['Value'])
               

            for index, itens in enumerate(clocks):
                data_e_hora_atuais = datetime.now()
                horario = data_e_hora_atuais.strftime(' %H:%M:%S')
                def ajuste(param):
                    param = param.split(" ")[0]
                    return param.replace(",",".")
                cpu = (
                    nome,
                    f'Core {index + 1}',
                    ajuste(clocks[index]),
                    ajuste(temperatures[index]),
                    ajuste(loads[index]),
                    horario
                )
                CPU.append(cpu)
                
            return CPU
        
   
