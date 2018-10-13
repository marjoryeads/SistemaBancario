/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utls;

import java.sql.Date;

/**
 *
 * @author Marjorye
 */
public class Historico {
    private float valor;
    private Date data;
    
    public Historico(){
    }

    public Historico(float valor, Date data) {
        this.valor = valor;
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    
}
