/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import model.Aluno;
import model.dao.AlunoDaoJpa;
import model.dao.DaoFactory;

/**
 *
 * @author isaac
 */


public class CadastrarAluno extends HttpServlet {

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

        String nome_aluno = request.getParameter("nome_aluno");
        String sobrenome_aluno = request.getParameter("sobrenome_aluno");
        String matricula_aluno = request.getParameter("matricula_aluno");

        if (nome_aluno.trim().equals("") || sobrenome_aluno.trim().equals("") || matricula_aluno.trim().equals("")) {
            response.sendRedirect("erroCampoVazio.html");
        } else {
            try {
                Aluno aluno = new Aluno();
                aluno.setNome(nome_aluno);
                aluno.setSobrenome(sobrenome_aluno);
                aluno.setMatricula(matricula_aluno);

                AlunoDaoJpa alunoNovo = DaoFactory.novoAlunoDao();
                alunoNovo.incluir(aluno);
                
               response.sendRedirect("index.html");
            } catch (Exception error) {
                response.sendRedirect("erroDeExcecao.html");
            }

        }

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
