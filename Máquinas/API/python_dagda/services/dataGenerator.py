import psutil
from datetime import datetime



def getHorario():
    data_e_hora_atuais = datetime.now()
    horario = data_e_hora_atuais.strftime('%Y-%m-%d %H:%M:%S')
    return horario
def getData():
  
    cpu_info = {
    'cpu': 0,
    'memory': 0,
    'disk': 0,
    'logic processors' :0,
    'frequency': 0,
    'horario': getHorario()
    }
    cpu = psutil.cpu_percent(interval=1, percpu=True)
    cpu_media = sum(cpu)/len(cpu)
    logic_processors = psutil.cpu_count(True)
    #frequency = psutil.cpu_freq(percpu=False).current
    memory = (psutil.virtual_memory().used >> 30)
    memory_percent = (psutil.virtual_memory().percent)
    disk = psutil.disk_usage('/').percent
    

    cpu_info['cpu'] = round(cpu_media,1)
    cpu_info['memory'] = memory
    cpu_info['memory_percent'] = memory_percent
    cpu_info['disk'] = disk
    cpu_info['logic processors'] = logic_processors
    #cpu_info['frequency'] = frequency

    #Objeto para visualização só
    print(cpu_info)
    #lista para envio no banco
    data = (round(cpu_media,1),logic_processors, memory, memory_percent, disk,getHorario())

    return data


