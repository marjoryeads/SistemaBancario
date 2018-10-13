/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utls;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
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

/**
 *
 * @author Marjorye
 */
@WebServlet(name = "Operacao", urlPatterns = {"/Operacao1"})
public class Operacao extends HttpServlet {

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
        String op=request.getParameter("op");
        String cpf=request.getParameter("cpf");
        
        response.getWriter().println("<!DOCTYPE HTML/> <html><head><title>Operação</title> </head> <body style='background:#FFFFFF'>");
        boolean resp=Metodos.autenticaCliente(request.getParameter("nConta"), request.getParameter("cpf"));
        
        if(resp==true){
        Metodos.cadastraOperacao(Float.parseFloat(request.getParameter("valor")), cpf, op);
        response.getWriter().println("<div align='center'>");
        response.getWriter().println("<h3>Operação efetuada com sucesso!</h3>");
        response.getWriter().println("<a href='index.html'><button>Voltar</button></a>");
        response.getWriter().println("</div>");
        }else{
         response.getWriter().println("<h3>Número da Conta ou CPF Inválido</h3>");
         response.getWriter().println("<a href='index.html'><button>Voltar</button></a>");
         response.getWriter().println("</div>");
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
