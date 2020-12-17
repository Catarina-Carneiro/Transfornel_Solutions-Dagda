import mysql.connector
# from services.pythohms import CrawlerOpenHardwareMonitor
# crawler = CrawlerOpenHardwareMonitor()

class Mysql:
    def __init__(self, user, password, host, database):
        self.user = user
        self.password = password
        self.host = host
        self.database = database
        self.mysql = None
        self.cursor = None

    #Estabelecendo uma conexão
    def connect(self):
        try:
            self.mysql = mysql.connector.connect(
            user=self.user, password=self.password, host=self.host, database=self.database,auth_plugin='mysql_native_password')
            #Criando cursor para manipulação do banco.
            print(self.mysql)
            self.cursor = self.mysql.cursor()
        except Exception as err:
            print(err)
            raise
    # faz os inserts no banco
    def insert(self,data,query):
        values = data
        try:
            print('Inserindo Valores')
            self.cursor.execute(query,values)
            self.mysql.commit()
        except Exception as err:
            print(err)
            self.mysql.rollback()
            self.close()

    def select(self,data,query):
        value = data
        try:
            print('Selecionando Valores')
            self.cursor.execute(query,value)
            print('Nome da maquina que vai verificar: ',value)

            
            return self.cursor

        except Exception as err:
            print(err)
            self.close()

    def close(self):
        self.mysql.close()