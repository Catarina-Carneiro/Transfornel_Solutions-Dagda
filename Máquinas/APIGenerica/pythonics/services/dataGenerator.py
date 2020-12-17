import psutil
from datetime import datetime

def getHorario():
    data_e_hora_atuais = datetime.now()
    horario = data_e_hora_atuais.strftime('%Y-%m-%d %H:%M:%S')
    return horario
    
def getData(dadosParaLeitura):
    dadosDaMaquina = dadosParaLeitura
    componenteParaLeitura = []

    # cpu_info = {
    #  'cpu': 0,
    # 'memory': 0,
    # 'disk': 0,
    # 'logic processors' :0,
    # 'frequency': 0,
    # 'horario': getHorario()
    # }
    
    cpu = psutil.cpu_percent(interval=1, percpu=True)
    cpu_media = sum(cpu)/len(cpu)
    memory = (psutil.virtual_memory().used >> 30)
    memory_percent = (psutil.virtual_memory().percent)
    disk = psutil.disk_usage('/').percent
    
    tipoComponente = dadosDaMaquina['tipoComponente']
    nomeLeitura = dadosDaMaquina['nomeLeitura']
    if tipoComponente == 'cpu':
        if nomeLeitura == 'uso':
            componenteParaLeitura.append(round(cpu_media,1))
    elif tipoComponente == 'ram':
        if nomeLeitura == "uso":
            componenteParaLeitura.append(memory_percent)
    elif tipoComponente == 'disco':
        if nomeLeitura == "uso":
            componenteParaLeitura.append(disk)

    # cpu_info['memory'] = memory
    #cpu_info['frequency'] = frequency
    #Objeto para visualização só
    # print(cpu_info)
    #lista para envio no banco
    # logic_processors = psutil.cpu_count(True)
    dados = []
    for index,item in enumerate(componenteParaLeitura):
        data = (
            componenteParaLeitura[index],
            getHorario(),
            dadosDaMaquina['idComponente']
        )
        dados.append(data)
    # data = (round(cpu_media,1),logic_processors, memory, memory_percent, disk,getHorario())

    return dados


