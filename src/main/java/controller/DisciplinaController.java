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
import model.Disciplina;
import model.Professor;
import model.dao.DaoFactory;
import model.dao.DisciplinaDaoJpa;
import model.dao.ProfessorDaoJpa;

/**
 *
 * @author isaac
 */
public class DisciplinaController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String prof_id = request.getParameter("professorId");
        String nome = request.getParameter("nome");
        
        int id = 0;

        if (prof_id != null) {
            id = Integer.parseInt(prof_id);
        }

        String acao = request.getParameter("acao");

        RequestDispatcher rd = null;

        switch (acao) {

            case "cadastro":

                ProfessorDaoJpa professorDao = DaoFactory.novoProfessorDao();
                List<Professor> professores = null;
                try {
                    professores = professorDao.listar();
                } catch (Exception ex) {
                    System.getLogger(DisciplinaController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }

                request.setAttribute("professores", professores);

                rd = request.getRequestDispatcher("/templates/disciplina/cadastro.jsp");
                rd.forward(request, response);

                break;

            case "inclusao":

                ProfessorDaoJpa profDao = DaoFactory.novoProfessorDao();
                Professor professor;
                try {
                    professor = profDao.pesquisarPorId(id);
                    Disciplina disciplina = new Disciplina();
                    disciplina.setProfessor(professor);
                    disciplina.setNome(nome);
                    DisciplinaDaoJpa DisciplinaNova = DaoFactory.novaDisciplinaDao();
                    disciplina.setProfessor(professor);
                    DisciplinaNova.incluir(disciplina);

                    response.sendRedirect("DisciplinaController?acao=listagem");
                   
                } catch (Exception ex) {
                    System.getLogger(DisciplinaController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }

                break;

            case "edicao":

                break;

            case "exclusao":

                break;

            case "listagem":
                DisciplinaDaoJpa dao = DaoFactory.novaDisciplinaDao();
                List<Disciplina> lista = null;

                try {
                    lista = dao.listar();
                } catch (Exception ex) {
                    System.getLogger(DisciplinaController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }

                request.setAttribute("disciplinas", lista);

                rd = request.getRequestDispatcher("/templates/disciplina/listagem.jsp");
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
