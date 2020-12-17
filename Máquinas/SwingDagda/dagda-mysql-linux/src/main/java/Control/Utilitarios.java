/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Rhian
 */
public class Utilitarios {
    public void inserirIcone(JFrame frm)
    {
        try {
            frm.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/seta.png"));
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
    
    
    }    
}
