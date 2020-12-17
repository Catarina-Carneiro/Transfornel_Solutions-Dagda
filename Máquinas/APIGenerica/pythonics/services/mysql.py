import mysql.connector


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

    # Essa função efetua o insert
    def insert(self, data):
        query = (
            "INSERT INTO Leituras(leitura,horaDeLeitura,fkMaqComp)VALUES(%s,%s,%s);"
        )
        values = data
        try:
            print('Inserindo Valores')
            self.cursor.execute(query,values)
            self.mysql.commit()
        except Exception as err:
            print(err)
            self.mysql.rollback()
            self.close()


    # Essa função efetua o select
    def select(self, data):
        query = (
            f"select ma.idMaquina, ma.nome, mc.idMaqComp, co.tipoComponente, mc.nome from Maquina as ma inner join MaqComp as mc on mc.fkMaquina = ma.idMaquina inner join Componente as co on mc.fkComponente = co.idComponente where ma.nome = '{data}';"
        )
        value = data        
        try:
            self.cursor.execute(query)
            return self.cursor
        except Exception as err:
            print(err)
            self.close()
    # Fechando conexão
    def close(self):
        self.mysql.close()

        


