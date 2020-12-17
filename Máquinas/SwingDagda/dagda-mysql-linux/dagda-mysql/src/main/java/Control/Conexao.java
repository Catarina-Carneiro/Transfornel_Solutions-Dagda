package Control;





import ADO.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rhian
 */
public class Conexao {
    //obrigatório tem que ter, copiar e colar
    private final String driver = "com.mysql.cj.jdbc.Driver"; // procura onde ta o banco no netbeans
    private final String url = "jdbc:mysql://localhost:3306/dagda?useTimezone=true&serverTimezone=UTC"; 
    private final String usuario = "rhian";	//usuario obrigatório
    private final String senha = "12345";//senha obrigatório
    private Connection conexao = null; //conexão do banco

    public Connection getConexao() {
        return conexao;
    }

    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }
    
    //metodo que conecta o banco de dados
    public boolean conexao() throws SQLException{
        try{
            Class.forName(driver);//tipo de conexao
            conexao = DriverManager.getConnection(url, usuario, senha);
            return true;
            
        }
        catch (ClassNotFoundException | SQLException ex){
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE,null,ex);
        }
        return false;
    }
	//desconectar o banco
     public void desconectar() throws SQLException{
         conexao.close();
     }
     
}
         
         
         
         
         
         
     
