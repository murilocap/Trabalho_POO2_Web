/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Professor;
import model.dao.DaoFactory;
import model.dao.ProfessorDaoJpa;

/**
 *
 * @author isaac
 */
public class ProfessorController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String acao = request.getParameter("acao");
        
        String nome = request.getParameter("nome");
        String matricula = request.getParameter("matricula");
        
        RequestDispatcher rd = null;

        switch (acao) {

            case "inclusao":
                
                try {
                   Professor prof = new Professor();
                    prof.setNome(nome);
                    prof.setMatricula(matricula);

                    ProfessorDaoJpa profNovo = DaoFactory.novoProfessorDao();
                    profNovo.incluir(prof);

                    response.sendRedirect("ProfessorController?acao=listagem");
                    return;
                } catch (Exception error) {
                    response.sendRedirect("erroDeExcecao.html");
                }
                
                break;

            case "edicao":

                break;

            case "exclusao":

                break;

            case "listagem":
                ProfessorDaoJpa dao = DaoFactory.novoProfessorDao();
                List<Professor> lista = null;
                
                try {
                    lista = dao.listar();
                } catch (Exception ex) {
                    System.getLogger(ProfessorController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }

                request.setAttribute("professores", lista);

                rd = request.getRequestDispatcher("/templates/professor/listagem.jsp");
                rd.forward(request, response);
                break;
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
