/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ADO.Componente;
import Control.Conexao;
import ADO.Usuario;
import ADO.Empresa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rhian
 */
public class Selects extends Conexao{
    private List<Usuario> listaUsu = new ArrayList<>();
    private List<Empresa> listaEmpresa = new ArrayList<>();
    private List<Componente> listaComp = new ArrayList<>();
    
    public List<Usuario> selecionar(Usuario usu,String senha,String email) throws SQLException{
         
         if (conexao()){
             
             String aux= String.format("SELECT * FROM usuario WHERE email = '%s' AND senha = '%s'",email,senha);
                    
             Statement st = getConexao().createStatement();
             st.executeQuery(aux);
             ResultSet resultado = st.getResultSet();
             while (resultado.next()) {
                 usu.setEmail(resultado.getString("email"));
                 usu.setId(resultado.getInt("idUsuario"));
                 usu.setNome(resultado.getString("nome"));
                 usu.setFkEmpresa(resultado.getInt("fkEmpresa"));
                 listaUsu.add(usu);
             }
             desconectar();
         }
         return  listaUsu;
            
        
     }
    public List<Empresa> selecionar(Empresa empresa,Integer fkEmpresa) throws SQLException{
         
         if (conexao()){
             
             String aux= String.format("SELECT * FROM empresa where idEmpresa = '%d'",fkEmpresa);
                    
             Statement st = getConexao().createStatement();
             st.executeQuery(aux);
             ResultSet resultado = st.getResultSet();
             while (resultado.next()) {
                 empresa.setNome(resultado.getString("nome"));
                 empresa.setIdEmpresa(resultado.getInt("idEmpresa"));
                 empresa.setRua(resultado.getString("rua"));
                 empresa.setPais(resultado.getString("pais"));
                 empresa.setEstado(resultado.getString("estado"));
                 empresa.setBairro(resultado.getString("bairro"));
                 empresa.setCnpj(resultado.getString("cnpj"));
                 empresa.setCidade(resultado.getString("cidade"));
                 empresa.setCep(resultado.getString("cep"));
                 empresa.setTelefone(resultado.getString("telefone"));
                 empresa.setRepresentante(resultado.getString("representante"));
                 listaEmpresa.add(empresa);
                 

             }
             desconectar();
         }
         return  listaEmpresa;
            
        
     }
    public List<Componente> selecionar(Componente componente, String nomeComponente) throws  SQLException{
        if (conexao()){
             
             String aux= String.format("SELECT * FROM Componente WHERE tipoComponente = '%s'",nomeComponente);
                    
             Statement st = getConexao().createStatement();
             st.executeQuery(aux);
             ResultSet resultado = st.getResultSet();
             while (resultado.next()) {
                componente.setIdComponente(resultado.getInt("idComponente"));
                componente.setTipoComponente(resultado.getString("tipoComponente"));
                componente.setMetrica(resultado.getString("metrica"));
                listaComp.add(componente);
             }
             desconectar();
         }
         return  listaComp;
    
    }
      
}
