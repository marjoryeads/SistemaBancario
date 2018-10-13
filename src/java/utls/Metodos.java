/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utls;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marjorye
 */
public class Metodos {
    
    public static void cadastraOperacao(float valor, String cpf, String op){
        if(op.equalsIgnoreCase("debito")){
        valor=valor*(-1);
        }
        
      try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/sistema_bancario","root","");
            java.sql.PreparedStatement ps= conn.prepareStatement("insert into operacao(dataOperacao, valorOperacao,cpfResponsavelOperacao,idContaCorrente) values (current_timestamp(),?,?,?)");
                     
           
            ps.setFloat(1, valor);
            ps.setString(2, cpf);
            ps.setInt(3, Metodos.retornaIdConta(cpf));
                       
            ps.execute();
            
           
            
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar operação");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContaCorrente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static int retornaIdConta(String cpf){
        int resultado=0;
    try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/sistema_bancario","root","");
            java.sql.PreparedStatement ps= conn.prepareStatement("select id from contacorrente where cpfTitular=?");
            ps.setString(1, cpf);
            ResultSet R= ps.executeQuery();
            
            while(R.next()){
            resultado=R.getInt("id");
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ContaCorrente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContaCorrente.class.getName()).log(Level.SEVERE, null, ex);
        }
   return resultado;
    }
    
    public static void historicoOp(String cpf){
        
       try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/sistema_bancario","root","");
            java.sql.PreparedStatement ps= conn.prepareStatement("select * from operacoes where cpfTitular=?");
            ps.setString(1, cpf);
            ResultSet R= ps.executeQuery();
            
            while(R.next()){
            
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ContaCorrente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContaCorrente.class.getName()).log(Level.SEVERE, null, ex);
        }   
    
    }
    
    
    public static float calculaSaldo(String cpf){
        float saldo=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/sistema_bancario","root","");
            java.sql.PreparedStatement ps= conn.prepareStatement("SELECT SUM(valorOperacao) AS 'Saldo' FROM operacao WHERE cpfResponsavelOperacao = ?");
            ps.setString(1, cpf);
            ResultSet R= ps.executeQuery();
            
            while(R.next()){
            saldo=R.getFloat("Saldo");
            }
            
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ContaCorrente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContaCorrente.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        return saldo;
    }
    
    public static boolean autenticaCliente( String numConta,String cpf){
       boolean resultado=true;
       
       
    try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/sistema_bancario","root","");
            java.sql.PreparedStatement ps= conn.prepareStatement("select * from contacorrente where cpfTitular=? and numeroConta=?");
            ps.setString(1, cpf);
            ps.setString(2, numConta);
            ResultSet R= ps.executeQuery();
            
            if(R.next()){
            ps= conn.prepareStatement("select * from contaCorrente where cpf="+cpf);
            ps.clearParameters();  
            R=ps.executeQuery();  
            resultado= true;
            }else{
            resultado= false;
            } 
        } catch (SQLException ex) {
            Logger.getLogger(ContaCorrente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContaCorrente.class.getName()).log(Level.SEVERE, null, ex);
        }
    return resultado;
    }
    
}
