/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import ADO.Leituras;
import ADO.MaqComp;
import Control.Conexao;
import Control.Create_Grafico;
import Jira.ClienteJiraApi;
import Jira.Issue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Panel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author Aluno
 */
public class Grafico extends JFrame {
    private Integer fkMaqComp;
    private String nome;
    private List<Leituras> l = new ArrayList<>();
    private Create_Grafico cg = new Create_Grafico();
    private Conexao c = new Conexao();
    private Integer alerta;
    private Integer contador = 0;
    private String issueString;
    private String nomeComp;
    private boolean criar = false;
    /**
     * Creates new form Grafico
     * @param i
     */
    public Grafico(Integer i,String nome,String nomeComp) {
       this.fkMaqComp = i;
       this.nome = nome;
       this.nomeComp = nomeComp;
        try {
           initUI(); 
        } catch (Exception e) {
            System.out.println(e);
        }
        // nao ta pegando o maqComp
        
    }
    public void grafico() throws SQLException{
        XYDataset dataset = null;
        l = c.selecionar(fkMaqComp);
        dataset = cg.createDataset(l,fkMaqComp);
        JFreeChart chart = cg.createChart(dataset,nome);
        
        ChartPanel chartPanel;
        
        chartPanel = new ChartPanel(chart){ 
        public Dimension getPreferredSize() {
            return new Dimension(700, 500);
        }
    };
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.BLACK);
        chartPanel.setForeground(Color.BLACK);
        add(chartPanel);
        
        
        

        pack();
        setTitle("Gráfico de maquina");
        setLocationRelativeTo(null);  
        
        
    }
    public void Alertando() throws SQLException, IOException{
        List<MaqComp> listaMaq = c.selecionarAlerta(fkMaqComp);
        
        listaMaq.forEach(lista->{
            alerta = Integer.parseInt(lista.getAlerta());
        });
                
        l.forEach(ac->{
        if(ac.getLeitura() > alerta){
            criar = true;
        }
        });
        if(criar){
            contador++;
            JOptionPane.showMessageDialog(null, "alerta no jira criado");

                Gson gson = new GsonBuilder().setPrettyPrinting().create();

            ClienteJiraApi clienteJiraApi = new ClienteJiraApi(
                    "dagda09.atlassian.net",
                    "201grupo9c@bandtec.com.br",
                    "4aNFcTTOALqkxj3iGgFg167B"
            );



            l.forEach(listaL->{

                issueString = nome + " em " + listaL.getLeitura();
            });


            // Exemplo de objeto para novo chamado (Issue)
            Issue novaIssue = new Issue();
            novaIssue.setProjectKey("SD");
            novaIssue.setSummary(nomeComp);
            novaIssue.setDescription(issueString);
            novaIssue.setLabels("alerta-"+nome);

                try {
                    clienteJiraApi.criarIssue(novaIssue);
                } catch (IOException ex) {
                    Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                }
            System.out.println("Issue criada: "+gson.toJson(novaIssue));
             Processos p = new Processos(this,true);
            p.setVisible(true);
            }
       
        
    }
    public void initUI() throws SQLException {
        grafico();
        
      
        
        Thread thread = new Thread(){
        @Override
        public void run(){
            while (true) {                
                try { Thread.sleep (1000); 
                if(contador == 0){
                    
                    Alertando();
                    grafico();
                    
                }else{
                
                        grafico();
                
                }
                
              
            } catch (InterruptedException ex) {
            } 
            catch (SQLException ex) {
                Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
            }   catch (IOException ex) {
                    Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        
        }
        
        
           
        };
        thread.start();
        
        
        
        
                }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 639, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 289, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                
            
            }
        });
    }

    private JFreeChart createChart(XYDataset dataset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
