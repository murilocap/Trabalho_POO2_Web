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

public class ProfessorController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        response.setContentType("text/html;charset=UTF-8");

        String acao = request.getParameter("acao");
        String idParam = request.getParameter("id");
        String nome = request.getParameter("nome");
        String matricula = request.getParameter("matricula");

        ProfessorDaoJpa dao = DaoFactory.novoProfessorDao();

        RequestDispatcher rd = null;

        switch (acao) {

            case "inclusao":
                try {

                    Professor professor = new Professor();
                    professor.setNome(nome);
                    professor.setMatricula(matricula);

                    if (idParam == null || idParam.isEmpty()) {

                        dao.incluir(professor);

                    } else {

                        professor.setId(Integer.parseInt(idParam));
                        dao.editar(professor);

                    }

                    response.sendRedirect("ProfessorController?acao=listagem");
                    return;

                } catch (Exception error) {

                    response.sendRedirect("erroDeExcecao.html");

                }

                break;

            case "edicao":

                Professor professor = dao.pesquisarPorId(Integer.parseInt(idParam));

                request.setAttribute("professor", professor);

                rd = request.getRequestDispatcher("/templates/professor/cadastro.jsp");
                rd.forward(request, response);

                return;

            case "exclusao":

                try {

                    dao.excluir(Integer.parseInt(idParam));

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
