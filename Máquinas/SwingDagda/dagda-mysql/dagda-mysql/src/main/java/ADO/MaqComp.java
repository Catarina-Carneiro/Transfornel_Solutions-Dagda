/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADO;

/**
 *create table Maq_Comp(
	idMaqComp INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45),
    alerta VARCHAR(6),
    fkMaquina INT,
    fkComponente INT,
    FOREIGN KEY(fkMaquina) REFERENCES Maquina(idMaquina),
    FOREIGN KEY(fkComponente) REFERENCES Componente(idComponente)
 * @author Rhian
 */
public class MaqComp {
    private Integer idMaqComp;
    private String nome;
    private String alerta;
    private Integer fkMaquina;
    private Integer fkComponente;

    public Integer getIdMaqComp() {
        return idMaqComp;
    }

    public void setIdMaqComp(Integer idMaqComp) {
        this.idMaqComp = idMaqComp;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }

    public Integer getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(Integer fkMaquina) {
        this.fkMaquina = fkMaquina;
    }

    public Integer getFkComponente() {
        return fkComponente;
    }

    public void setFkComponente(Integer fkComponente) {
        this.fkComponente = fkComponente;
    }
    
    
    
}
