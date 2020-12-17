/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;


import ADO.Componente;
import ADO.Empresa;
import ADO.Leituras;
import ADO.MaqComp;
import ADO.Maquina;
import ADO.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import org.apache.commons.dbcp2.BasicDataSource;
//import org.omg.CORBA.MARSHAL;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Rhian
 */
public class Conexao {
        
    protected JdbcTemplate jdbcTemplate;
    protected BasicDataSource dataSource;
    protected Connection connection;
    
    
    private String url = "jdbc:mysql://localhost:3306/dagda?serverTimezone="
            +TimeZone.getDefault().getID();
    //private String url = "jdbc:mysql://localhost:3306/dagda";
    private String username = "rhian";
    private String password = "12345";
    private String driver = "com.mysql.cj.jdbc.Driver";
    
    public Conexao(){
        createConnection();
    }
    private void createConnection(){
        this.dataSource = new BasicDataSource();
        this.dataSource.setDriverClassName(this.driver);
        this.dataSource.setUrl(url);
        this.dataSource.setUsername(username);
        this.dataSource.setPassword(password);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



  
    
    public List selecionar(Integer fkMaqComp) throws SQLException{
         
        List ler = new ArrayList();
        String aux= String.format("SELECT * FROM Leituras WHERE fkMaqComp = '%d'",fkMaqComp);
        ler = this.jdbcTemplate.query(aux, 
                new BeanPropertyRowMapper(Leituras.class));
        
        return ler;
        
     }
    
    public List selecionar(String valor) throws SQLException{
         
        List ler = new ArrayList();
        String aux= String.format("SELECT * FROM Componente WHERE tipoComponente = '%s'",valor);
        ler = this.jdbcTemplate.query(aux, 
                new BeanPropertyRowMapper(Componente.class));
        
        return ler;
        
     }
    public List selecionarLista(Integer fkMaq,Integer indice) throws SQLException{
   
        List ler = new ArrayList();
        String aux =  String.format("SELECT * FROM MaqComp WHERE fkMaquina = '%d'   AND fkComponente = '%d'" , fkMaq,indice);
        ler = this.jdbcTemplate.query(aux, 
                new BeanPropertyRowMapper(MaqComp.class));
        
        return ler;
        
     }
    public List selecionarAlerta(Integer idMaq) throws SQLException{
   
        List ler = new ArrayList();
        String aux =  String.format("SELECT * FROM MaqComp WHERE idMaqComp = '%d' " , idMaq );
        ler = this.jdbcTemplate.query(aux, 
                new BeanPropertyRowMapper(MaqComp.class));
        
        return ler;
        
     }
     public List selecionar(String senha,String email) throws SQLException{
         
        List ler = new ArrayList();
        String aux =  String.format( "SELECT * FROM Usuario WHERE email = '%s' AND senha = '%s' " , email, senha);
        ler = this.jdbcTemplate.query(aux, 
                new BeanPropertyRowMapper(Usuario.class));
        
        return ler;
        
     }
    public boolean selecionar(Integer fkMaq, Integer fkComp) throws SQLException{
         
        List ler = new ArrayList();
        String aux =  String.format("SELECT * FROM MaqComp WHERE fkMaquina = '%d' AND fkComponente = '%d'" , fkMaq, fkComp );
        ler = this.jdbcTemplate.query(aux, 
                new BeanPropertyRowMapper(MaqComp.class));
        
        if (ler.isEmpty()) {
            return true;
        }else{
            return false;
        }
        
     }
    
    
    
    public boolean inserir(String nome,String alerta,Integer fkMaq,Integer fkComp){
	try {
             connection = dataSource.getConnection();
            Statement stm = connection.createStatement();
	    stm.execute("Insert into MaqComp (nome,alerta,fkMaquina,fkComponente) values ('"+nome+"','"+alerta+"','"+fkMaq+"','"+fkComp+"')");
            return  true;
            } catch(Exception e){
		e.printStackTrace();
                return false;
 	}
            
	
    }
     public boolean deletar(Integer fkMaqComp){
	try {
             connection = dataSource.getConnection();
            Statement stm = connection.createStatement();
	    stm.execute(String.format("DELETE FROM Leituras WHERE fkMaqComp = %d ",fkMaqComp));
            return true;
            } catch(Exception e){
		e.printStackTrace();
               
 	}
        return false;
     }
    public boolean alterar(String nome,String alerta,Integer fkMaq,Integer fkComp,Integer idMaqComp){
	try {
            deletar(idMaqComp);
            connection = dataSource.getConnection();
            Statement stm = connection.createStatement();
          
	    stm.execute(String.format("UPDATE MaqComp SET nome = '%s', alerta = '%s',fkMaquina= '%d',fkComponente = '%d'  WHERE idMaqComp = %d ",nome,alerta,fkMaq,fkComp,idMaqComp));
            return  true;
            
            
            } catch(Exception e){
		e.printStackTrace();
                return false;
 	}
     
            
//	UPDATE tbl_livro
//SET Preco_Livro = 47.20
//WHERE ISBN = '9780735640610';
    }
    
    public boolean inserir(String nomePC,Integer fkEmpresa){
	try {
             connection = dataSource.getConnection();
            Statement stm = connection.createStatement();
	    stm.execute("Insert into Maquina (nome,fkEmpresa) values ('"+nomePC+"','"+fkEmpresa+"')");
            return  true;
            } catch(Exception e){
		e.printStackTrace();
                return false;
 	}
            
	
    }
    public boolean selecionarMaq(String nomePC) throws SQLException{
         
        List ler = new ArrayList();
        String aux =  String.format("SELECT * FROM Maquina WHERE nome = '%s' " , nomePC);
        ler = this.jdbcTemplate.query(aux, 
                new BeanPropertyRowMapper(Maquina.class));
        
        if (ler.isEmpty()) {
            return true;
        }else{
            return false;
        }
        
     }
    public List<Maquina> selecionarMaq1(String nomePC) throws SQLException{
         
        List<Maquina> ler = new ArrayList();
        String aux =  String.format("SELECT * FROM Maquina WHERE nome = '%s' " , nomePC);
        ler = this.jdbcTemplate.query(aux, 
                new BeanPropertyRowMapper(Maquina.class));
        return  ler;
     }
    public List selecionarEmpresa(Integer fkEmpresa){
         
        List ler = new ArrayList();
        String aux = String.format( "SELECT * FROM Empresa WHERE idEmpresa = '%d' " , fkEmpresa);
        ler = this.jdbcTemplate.query(aux, 
                new BeanPropertyRowMapper(Empresa.class));
        
        return ler;
        
     }
}

         
         
         
         
         
         



