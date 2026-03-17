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

        String acao      = request.getParameter("acao");
        String idParam   = request.getParameter("id");
        String nome      = request.getParameter("nome");
        String matricula = request.getParameter("matricula");

        int id = 0;

        AlunoDaoJpa dao = DaoFactory.novoAlunoDao();
        RequestDispatcher rd = null;

        Aluno aluno = new Aluno();
        if (nome != null && !nome.isEmpty()) {
            aluno.setNome(nome);
        }
        if (matricula != null && !matricula.isEmpty()) {
            aluno.setMatricula(matricula);
        }
        if (idParam != null && !idParam.isEmpty()) {
            id = Integer.parseInt(idParam);
            aluno.setId(id);
        }

        switch (acao) {

            case "formularioInclusao":
                rd = request.getRequestDispatcher("/templates/aluno/cadastro.jsp");
                rd.forward(request, response);
                return;

            case "inclusao":
                try {
                    dao.incluir(aluno);
                    response.sendRedirect("AlunoController?acao=listagem");
                    return;
                } catch (Exception error) {
                    response.sendRedirect("erroDeExcecao.html");
                }
                break;

            case "formularioEdicao":
                Aluno alunoForm = dao.pesquisarPorId(id);
                request.setAttribute("aluno", alunoForm);
                rd = request.getRequestDispatcher("/templates/aluno/edicao.jsp");
                rd.forward(request, response);
                return;

            case "edicao":
                try {
                    dao.editar(aluno);
                    response.sendRedirect("AlunoController?acao=listagem");
                    return;
                } catch (Exception error) {
                    response.sendRedirect("erroDeExcecao.html");
                }
                break;

            case "exclusao":
                try {
                    dao.excluir(id);
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
