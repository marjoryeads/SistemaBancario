/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Date;

/**
 *
 * @author Marjorye
 */
@WebServlet(name = "Extrato", urlPatterns = {"/Extrato1"})
public class Extrato extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
         
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
       
        response.getWriter().println("<!DOCTYPE HTML/> <html><head><title>Extrato</title> </head> <body style='background:#E0FFFF'>");
        String conta=request.getParameter("nConta")+"-"+request.getParameter("nDig");
        
        boolean resp=Metodos.autenticaCliente(conta, request.getParameter("cpf"));
          
        if(resp==true){
                
            try {
                response.getWriter().println("<div align='center'>");
                response.getWriter().println("<h2>Extrato da conta</h2>"+"<h3> Número da conta: "+request.getParameter("nConta")+"</h3>");
                response.getWriter().println("<h3> CPF do Responsável: "+request.getParameter("cpf")+"</h2>");
                
                response.getWriter().print("<table>");
                response.getWriter().print("<tr><td>Histórico de Operações</td><tr>");
                response.getWriter().print("<tr><td>Valor</td>");
                response.getWriter().print("<td>Data da Operação</td><tr>");
                
                 
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/sistema_bancario","root","");
                PreparedStatement ps= (PreparedStatement)conn.prepareStatement("select id from contacorrente where numeroConta=?");
                PreparedStatement ps1;
                ps.setString(1, conta);
                ResultSet r= ps.executeQuery();
                int idResult=0;
                
                while(r.next()){
                idResult=r.getInt("id");
                }
               
               
                ps1=(PreparedStatement) conn.prepareStatement("select * from operacao where idContaCorrente="+idResult);
                ResultSet r2d2=ps1.executeQuery();
               
                while(r2d2.next()){
                
                response.getWriter().println("<tr><td>"+r2d2.getString(String.valueOf("valorOperacao"))+"</td>");
                response.getWriter().println("<td>"+r2d2.getString(String.valueOf("dataOperacao"))+"</td></tr>");
                }
                
                  response.getWriter().println("<tr><td><h4>Saldo:R$ "+Metodos.calculaSaldo(request.getParameter("cpf"))+"</h5></td></tr>");
                  response.getWriter().print("</table>");
                  
                  
                  
                  response.getWriter().println("<a href='index.html'><button>Voltar</button></a>");
                  response.getWriter().println("</div>");
                  response.getWriter().println("</body></html>");
              
               
            } catch (SQLException ex) {
                Logger.getLogger(Extrato.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Extrato.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                  
                  
                  
        }else{
        response.getWriter().println("<h3>Número da conta ou CPF inválido!</h3>");
        response.getWriter().println("<a href='index.html'><button>Voltar</button></a>");
        }
        response.getWriter().println("</body></html>");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
