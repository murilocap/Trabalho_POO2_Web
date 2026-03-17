package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Disciplina;
import model.Professor;
import model.dao.DaoFactory;
import model.dao.DisciplinaDaoJpa;
import model.dao.ProfessorDaoJpa;

import java.io.IOException;
import java.util.List;

public class DisciplinaController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        response.setContentType("text/html;charset=UTF-8");

        String acao = request.getParameter("acao");
        String idParam = request.getParameter("id");

        String nome = request.getParameter("nome");
        String professorId = request.getParameter("professorId");

        DisciplinaDaoJpa dao = DaoFactory.novaDisciplinaDao();
        ProfessorDaoJpa professorDao = DaoFactory.novoProfessorDao();

        RequestDispatcher rd = null;

        switch (acao) {

            case "inclusao":

                try {

                    Disciplina disciplina = new Disciplina();

                    disciplina.setNome(nome);

                    if (professorId != null) {
                        Professor professor = professorDao.pesquisarPorId(Integer.parseInt(professorId));
                        disciplina.setProfessor(professor);
                    }

                    if (idParam == null || idParam.isEmpty()) {

                        dao.incluir(disciplina);

                    } else {

                        disciplina.setId(Integer.parseInt(idParam));
                        dao.editar(disciplina);

                    }

                    response.sendRedirect("DisciplinaController?acao=listagem");
                    return;

                } catch (Exception error) {

                    response.sendRedirect("erroDeExcecao.html");

                }

                break;

            case "edicao":

                Disciplina disciplina = dao.pesquisarPorId(Integer.parseInt(idParam));

                request.setAttribute("disciplina", disciplina);

                // necessário para preencher o select
                request.setAttribute("professores", professorDao.listar());

                rd = request.getRequestDispatcher("/templates/disciplina/cadastro.jsp");
                rd.forward(request, response);

                return;

            case "exclusao":

                try {

                    dao.excluir(Integer.parseInt(idParam));

                    response.sendRedirect("DisciplinaController?acao=listagem.jsp");
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
                
                
            case "listar-cadastro":
                
                
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
