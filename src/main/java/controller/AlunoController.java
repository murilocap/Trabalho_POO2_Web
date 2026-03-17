package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Aluno;
import model.dao.AlunoDaoJpa;
import model.dao.DaoFactory;

import java.io.IOException;
import java.util.List;

public class AlunoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");

        String acao = request.getParameter("acao");
        String idParam = request.getParameter("id");
        String nome = request.getParameter("nome");
        String matricula = request.getParameter("matricula");

        AlunoDaoJpa dao = DaoFactory.novoAlunoDao();

        RequestDispatcher rd = null;

        switch (acao) {

            case "inclusao":
                try {
                    Aluno aluno = new Aluno();
                    aluno.setNome(nome);
                    aluno.setMatricula(matricula);

                    if (idParam == null || idParam.isEmpty()) {
                        dao.incluir(aluno);
                    } else {
                        int id = Integer.parseInt(idParam);
                        aluno.setId(id);
                        dao.editar(aluno);
                    }

                    response.sendRedirect("AlunoController?acao=listagem");
                    return;
                } catch (Exception error) {
                    response.sendRedirect("erroDeExcecao.html");
                }

                break;

            case "edicao":
                Aluno aluno = dao.pesquisarPorId(Integer.parseInt(idParam));

                request.setAttribute("aluno", aluno);

                rd = request.getRequestDispatcher("/templates/aluno/edicao.jsp");
                rd.forward(request, response);

                return;

            case "exclusao":
                try {
                    dao.excluir(Integer.parseInt(idParam));
                    response.sendRedirect("AlunoController?acao=listagem");
                    return;
                } catch (Exception error) {
                    response.sendRedirect("erroDeExcecao.html");
                }

                break;

            case "listagem":
                List<Aluno> lista = null;

                try {
                    lista = dao.listar();
                } catch (Exception ex) {
                    System.getLogger(AlunoController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }

                request.setAttribute("alunos", lista);

                rd = request.getRequestDispatcher("/templates/aluno/listagem.jsp");
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
