/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utls;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marjorye
 */
@WebServlet(name = "ContaCorrente", urlPatterns = {"/ContaCorrente1"})
public class ContaCorrente extends HttpServlet {

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
               
       String conta= Metodos.criaConta(request.getParameter("nome"), request.getParameter("cpf"), response);
         
             response.getWriter().println("<!DOCTYPE HTML/> <html><head><title>Conta Corrente</title> </head> <body style='background:#FFFFFF'>");
             response.getWriter().println("<div ALIGN='center'>");
             response.getWriter().println("<font face='calibri' color='#4682B4' size='6'>Cadastro efetuado com sucesso!</font></br>");
             response.getWriter().println("<font face='calibri' color='#000000' size='5'>Seu número da conta:"+conta+" </font></br>");
             response.getWriter().println("<font face='calibri' color='#000000' size='4'>Anote o número da sua conta e não perca.</br>As operações e emissão de extrato são feitas a partir desse número e do seu CPF. </font></br>");
             response.getWriter().println("<a href='index.html'><button>Voltar</button></a>");
             response.getWriter().println("</div>");
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
