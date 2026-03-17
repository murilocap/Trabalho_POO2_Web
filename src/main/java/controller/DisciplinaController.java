package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Disciplina;
import model.dao.DaoFactory;
import model.dao.DisciplinaDaoJpa;
import model.dao.ProfessorDaoJpa;
import java.io.IOException;
import java.util.List;

public class DisciplinaController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");

        String acao         = request.getParameter("acao");
        String idParam      = request.getParameter("id");
        String nome         = request.getParameter("nome");
        String professorId  = request.getParameter("professorId");

        int id = 0;

        ProfessorDaoJpa professorDao = DaoFactory.novoProfessorDao();
        DisciplinaDaoJpa dao = DaoFactory.novaDisciplinaDao();
        RequestDispatcher rd = null;

        Disciplina disciplina = new Disciplina();
        if (nome != null && !nome.isEmpty()) {
            disciplina.setNome(nome);
        }
        if (professorId != null) {
            int idProf = Integer.parseInt(professorId);
            disciplina.setProfessor(
                professorDao.pesquisarPorId(idProf)
            );
        }
        if (idParam != null && !idParam.isEmpty()) {
            id = Integer.parseInt(idParam);
            disciplina.setId(id);
        }

        switch (acao) {

            case "formularioInclusao":
                request.setAttribute("professores", professorDao.listar());
                rd = request.getRequestDispatcher("/templates/disciplina/cadastro.jsp");
                rd.forward(request, response);
                return;

            case "inclusao":
                try {
                    dao.incluir(disciplina);
                    response.sendRedirect("DisciplinaController?acao=listagem");
                    return;
                } catch (Exception error) {
                    response.sendRedirect("erroDeExcecao.html");
                }
                break;

            case "formularioEdicao":
                Disciplina disciplinaForm = dao.pesquisarPorId(id);
                request.setAttribute("disciplina", disciplinaForm);
                request.setAttribute("professores", professorDao.listar());
                rd = request.getRequestDispatcher("/templates/disciplina/edicao.jsp");
                rd.forward(request, response);
                return;

            case "edicao":
                try {
                    dao.editar(disciplina);
                    response.sendRedirect("DisciplinaController?acao=listagem");
                    return;
                } catch (Exception error) {
                    response.sendRedirect("erroDeExcecao.html");
                }
                break;

            case "exclusao":
                try {
                    dao.excluir(id);
                    response.sendRedirect("DisciplinaController?acao=listagem");
                    return;
                } catch (Exception error) {
                    response.sendRedirect("erroDeExcecao.html");
                }
                break;

            case "listagem":
                List<Disciplina> lista = dao.listar();
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
