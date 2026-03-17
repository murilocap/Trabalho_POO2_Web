package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Professor;
import model.dao.DaoFactory;
import model.dao.ProfessorDaoJpa;
import java.io.IOException;
import java.util.List;

public class ProfessorController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");

        String acao      = request.getParameter("acao");
        String idParam   = request.getParameter("id");
        String nome      = request.getParameter("nome");
        String matricula = request.getParameter("matricula");

        int id = 0;

        ProfessorDaoJpa dao = DaoFactory.novoProfessorDao();
        RequestDispatcher rd = null;

        Professor professor = new Professor();
        if (nome != null && !nome.isEmpty()) {
            professor.setNome(nome);
        }
        if (matricula != null && !matricula.isEmpty()) {
            professor.setMatricula(matricula);
        }
        if (idParam != null && !idParam.isEmpty()) {
            id = Integer.parseInt(idParam);
            professor.setId(id);
        }

        switch (acao) {

            case "formularioInclusao":
                rd = request.getRequestDispatcher("/templates/professor/cadastro.jsp");
                rd.forward(request, response);
                return;

            case "inclusao":
                try {
                    dao.incluir(professor);
                    response.sendRedirect("ProfessorController?acao=listagem");
                    return;
                } catch (Exception error) {
                    response.sendRedirect("erroDeExcecao.html");
                }
                break;

            case "formularioEdicao":
                Professor professorForm = dao.pesquisarPorId(id);
                request.setAttribute("professor", professorForm);
                rd = request.getRequestDispatcher("/templates/professor/edicao.jsp");
                rd.forward(request, response);
                return;

            case "edicao":
                try {
                    dao.editar(professor);
                    response.sendRedirect("ProfessorController?acao=listagem");
                    return;
                } catch (Exception error) {
                    response.sendRedirect("erroDeExcecao.html");
                }
                break;

            case "exclusao":
                try {
                    dao.excluir(id);
                    response.sendRedirect("ProfessorController?acao=listagem");
                    return;
                } catch (Exception error) {
                    response.sendRedirect("erroDeExcecao.html");
                }
                break;

            case "listagem":
                List<Professor> lista = dao.listar();
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
        try {
            processRequest(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
