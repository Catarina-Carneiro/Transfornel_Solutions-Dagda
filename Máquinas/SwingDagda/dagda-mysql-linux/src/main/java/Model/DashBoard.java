/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import ADO.Componente;
import ADO.Empresa;
import ADO.MaqComp;
import ADO.Maquina;
import ADO.Usuario;
import Control.Conexao;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;


/**
 *
 * @author Rhian
 */
public class DashBoard extends javax.swing.JFrame {
    private  List<Usuario> listaUsu;
    private DefaultListModel lista = new DefaultListModel();
    private Integer fkMaquina;
    private List<Maquina> listaMaquina =  new ArrayList<>();
    private List<Empresa> listaEmpresa =  new ArrayList<>();
    private Conexao selects = new Conexao();
    private List<Componente> listaComp = new ArrayList<>();
    private Integer fkComp;
    private Integer fkMaqComp;
    private List<MaqComp> l = new ArrayList<>();
   
    /**
     * Creates new form DashBoard
     */
    public void Atualizar(){
        
       
        listaUsu.forEach(usuario -> {
            lbBem.setText(usuario.getNome());
            try {
                listaMaquina = selects.selecionarMaq1(InetAddress.getLocalHost().getHostName());
                listaMaquina.forEach(lista->{
                    fkMaquina = lista.getIdMaquina();
                
                });
            } catch (UnknownHostException ex) {
                Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
       try {
            
            tfNomeMaq.setText(InetAddress.getLocalHost().getHostName());
      } catch (UnknownHostException e) {
            e.printStackTrace();
      }
    }
    public void logout(){
        listaUsu.clear();
        dispose();
        LoginDagda loginDagda = new LoginDagda();
        loginDagda.setVisible(true);
    }
    public void buscar(String valor,String valor2,String alerta){
        listaComp.clear();
        valor = valor.toLowerCase();
        valor2 = valor2.toLowerCase();
      
        try {
            listaComp = selects.selecionar(valor);
            listaComp.forEach(componente ->{
                fkComp = componente.getIdComponente();
                
               
            });
             Integer indice = comComp.getSelectedIndex() + 1;
        
         l = selects.selecionarLista( fkMaquina,indice);
       
            l.forEach(listinha ->{
                
                 fkMaqComp = listinha.getIdMaqComp();
                
            });
             try {
                    if(selects.selecionar( fkMaquina, fkComp)){
                        System.out.println(valor2);
                         if(selects.inserir(valor2,alerta,fkMaquina,fkComp)){
                             System.out.println("Inserido com sucesso");
                         }else{
                             System.out.println("Não inserido");
                         }
                    }else{
                        
                        if(selects.alterar(valor2, alerta, fkMaquina, fkComp,fkMaqComp)){
                            System.out.println("alterado com sucesso");
                        }
                            else{
                                  System.out.println("Não alterado");  
                                    
                                    }}
                    }
                     
         catch (SQLException ex) {
            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }}catch (SQLException ex) {
            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        
    }
    
    public void ajustarList(String alerta){
        String valor = (String) comComp.getSelectedItem();
        String valor2 = (String) comTipo.getSelectedItem();
       
          
            buscar(valor,valor2,alerta);
        
        
        }
        
      
    
  
 
    public DashBoard(List<Usuario> lista) {
        initComponents();
        this.listaUsu = lista;
        Atualizar();
        setTitle("Dashboard");
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnContainerTela = new javax.swing.JPanel();
        pnBarMenu = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        lbBem = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        normal = new javax.swing.JLabel();
        pnDadosMaquina = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnSelecao = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        comComp = new javax.swing.JComboBox<>();
        tfAlerta = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        comTipo = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        btnSelecionar = new javax.swing.JButton();
        tfNomeMaq = new javax.swing.JLabel();
        btnInfo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnContainerTela.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnBarMenu.setBackground(new java.awt.Color(253, 203, 110));

        jButton1.setBackground(new java.awt.Color(30, 39, 46));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/exitIcon.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jButton1.setFocusPainted(false);
        jButton1.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton1.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton1.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lbBem.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbBem.setText("Fulano");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconeDagda.png"))); // NOI18N

        normal.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        normal.setText("Bem vindo,");

        javax.swing.GroupLayout pnBarMenuLayout = new javax.swing.GroupLayout(pnBarMenu);
        pnBarMenu.setLayout(pnBarMenuLayout);
        pnBarMenuLayout.setHorizontalGroup(
            pnBarMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBarMenuLayout.createSequentialGroup()
                .addGroup(pnBarMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnBarMenuLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnBarMenuLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnBarMenuLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(pnBarMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnBarMenuLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lbBem))
                            .addComponent(normal))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        pnBarMenuLayout.setVerticalGroup(
            pnBarMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBarMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(normal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbBem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        pnContainerTela.add(pnBarMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 390));

        pnDadosMaquina.setBackground(new java.awt.Color(30, 39, 46));
        pnDadosMaquina.setPreferredSize(new java.awt.Dimension(745, 550));
        pnDadosMaquina.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(236, 240, 241));
        jLabel1.setText("Nome da máquina");
        pnDadosMaquina.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        pnSelecao.setBackground(new java.awt.Color(30, 39, 46));
        pnSelecao.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(236, 240, 241));
        jLabel3.setText("Selecione o que deseja monitorar");
        pnSelecao.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, 25));

        comComp.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        comComp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CPU", "Ram", "Disco" }));
        comComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comCompActionPerformed(evt);
            }
        });
        pnSelecao.add(comComp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 210, 35));
        pnSelecao.add(tfAlerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 74, 33));

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(236, 240, 241));
        jLabel6.setText("Indice de alerta de uso :");
        pnSelecao.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, -1, -1));

        comTipo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        comTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "uso" }));
        comTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comTipoActionPerformed(evt);
            }
        });
        pnSelecao.add(comTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 210, 35));

        jButton2.setBackground(new java.awt.Color(30, 39, 46));
        jButton2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphIcon.png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(210, 27));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        pnSelecao.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 210, 80));

        btnSelecionar.setBackground(new java.awt.Color(30, 39, 46));
        btnSelecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/playIcone.png"))); // NOI18N
        btnSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarActionPerformed(evt);
            }
        });
        pnSelecao.add(btnSelecionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 210, 80));

        pnDadosMaquina.add(pnSelecao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 480, 300));

        tfNomeMaq.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        tfNomeMaq.setForeground(new java.awt.Color(236, 240, 241));
        tfNomeMaq.setText("Nome da Maquina");
        pnDadosMaquina.add(tfNomeMaq, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        btnInfo.setBackground(new java.awt.Color(30, 39, 46));
        btnInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infoIcone.png"))); // NOI18N
        btnInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoActionPerformed(evt);
            }
        });
        pnDadosMaquina.add(btnInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 35, 35));

        pnContainerTela.add(pnDadosMaquina, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 480, 390));

        getContentPane().add(pnContainerTela, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 390));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed
        // TODO add your handling code here:
        listaEmpresa = selects.selecionarEmpresa(fkMaquina);
        InfoEmpresa infoEmpresa = new InfoEmpresa(this,true,listaEmpresa);
        infoEmpresa.setVisible(true);

    }//GEN-LAST:event_btnInfoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String nomeComp = (String) comTipo.getSelectedItem();

        try {

            if(fkMaqComp == null){
                JOptionPane.showMessageDialog(null, "Componente não gravado para analise, por favor selecione ele mais acima");
            }else{
                Grafico dashBoard = new Grafico(fkMaqComp,nomeComp, (String) comComp.getSelectedItem());

                // abre a tela Dados
                dashBoard.setVisible(true);
            }
        } catch (Exception ex) {
            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void comTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comTipoActionPerformed

    private void comCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comCompActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comCompActionPerformed

    private void btnSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarActionPerformed
        // TODO add your handling code here:
        String a = tfAlerta.getText();
        if(comTipo.getSelectedItem() == "clock" && comComp.getSelectedItem() != "CPU"){
            JOptionPane.showMessageDialog(null, "clock é apenas do componente CPU");
        }else{
            if (!a.isEmpty()) {
                ajustarList(a);
                
               
                 String  comando = "/bin/bash -c  /home/ubuntu/Desktop/grupo-09-ccoa-20201"
                    + "/Máquinas/SwingDagda/dagda-mysql-linux/dagda-mysql/pythonExec.sh";
                
                try {
                    Runtime.getRuntime().exec(comando);
                } catch (IOException ex) {
                    Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                System.out.println("Insira um valor");
            }
        }
    }//GEN-LAST:event_btnSelecionarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        logout();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
             
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnSelecionar;
    private javax.swing.JComboBox<String> comComp;
    private javax.swing.JComboBox<String> comTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lbBem;
    private javax.swing.JLabel normal;
    private javax.swing.JPanel pnBarMenu;
    private javax.swing.JPanel pnContainerTela;
    private javax.swing.JPanel pnDadosMaquina;
    private javax.swing.JPanel pnSelecao;
    private javax.swing.JTextField tfAlerta;
    private javax.swing.JLabel tfNomeMaq;
    // End of variables declaration//GEN-END:variables
}
