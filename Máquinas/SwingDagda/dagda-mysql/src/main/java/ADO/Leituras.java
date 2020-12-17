/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADO;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Aluno
 */
public class Leituras {
    private Integer idLeitura;
    private Double Leitura;
    private Date horaLeitura;
    private  Integer fkMaqComp;

    public Integer getIdLeitura() {
        return idLeitura;
    }

    public void setIdLeitura(Integer idLeitura) {
        this.idLeitura = idLeitura;
    }

    public Double getLeitura() {
        return Leitura;
    }

    public void setLeitura(Double Leitura) {
        this.Leitura = Leitura;
    }

    public Date getHoraLeitura() {
        return horaLeitura;
    }

    public void setHoraLeitura(Date horaLeitura) {
        this.horaLeitura = horaLeitura;
    }

   

   

    public Integer getFkMaqComp() {
        return fkMaqComp;
    }

    public void setFkMaqComp(Integer fkMaqComp) {
        this.fkMaqComp = fkMaqComp;
    }
    
    
    
}
