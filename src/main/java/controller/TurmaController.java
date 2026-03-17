package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Aluno;
import model.Disciplina;
import model.Turma;
import model.dao.AlunoDaoJpa;
import model.dao.DaoFactory;
import model.dao.DisciplinaDaoJpa;
import model.dao.TurmaDaoJpa;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TurmaController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");

        String acao             = request.getParameter("acao");
        String idParam          = request.getParameter("id");
        String codTurma         = request.getParameter("cod_turma");
        String[] alunosId       = request.getParameterValues("alunosId");
        String[] disciplinasId  = request.getParameterValues("disciplinasId");

        int id = 0;
        // TESTE

        TurmaDaoJpa dao                 = DaoFactory.novaTurmaDao();
        AlunoDaoJpa alunoDao            = DaoFactory.novoAlunoDao();
        DisciplinaDaoJpa disciplinaDao  = DaoFactory.novaDisciplinaDao();
        RequestDispatcher rd = null;

        Turma turma = new Turma();
        if (codTurma != null && !codTurma.isEmpty()) {
            turma.setCod_turma(codTurma);
        }
        if (alunosId != null &&  alunosId.length > 0) {
            Collection<Aluno> listaAlunos = new ArrayList<>();
            for (String a_id : alunosId) {
                listaAlunos.add(
                    alunoDao.pesquisarPorId(Integer.parseInt(a_id))
                );
            }
            turma.setAlunos(listaAlunos);
        }
        if (disciplinasId != null &&  disciplinasId.length > 0) {
            Collection<Disciplina> listaDisciplinas = new ArrayList<>();
            for (String d_id : disciplinasId) {
                listaDisciplinas.add(
                    disciplinaDao.pesquisarPorId(Integer.parseInt(d_id))
                );
            }
            turma.setDisciplinas(listaDisciplinas);
        }
        if (idParam != null && !idParam.isEmpty()) {
            id = Integer.parseInt(idParam);
            turma.setId(id);
        }

        switch (acao) {

            case "formularioInclusao":
                request.setAttribute("alunos", alunoDao.listar());
                request.setAttribute("disciplinas", disciplinaDao.listar());
                rd = request.getRequestDispatcher("/templates/turma/cadastro.jsp");
                rd.forward(request, response);
                return;

            case "inclusao":
                try {

                    Turma turmaInclusao = new Turma();
                    turmaInclusao.setCod_turma(codTurma);

                    // alunos
                    if (alunosId != null) {
                        Collection<Aluno> listaAlunos = new ArrayList<>();
                        for (String a_id : alunosId) {
                            listaAlunos.add(alunoDao.pesquisarPorId(Integer.parseInt(a_id)));
                        }
                        turmaInclusao.setAlunos(listaAlunos);
                    }

                    if (disciplinasId != null) {
                        Collection<Disciplina> listaDisciplinas = new ArrayList<>();
                        for (String d_id : disciplinasId) {
                            listaDisciplinas.add(disciplinaDao.pesquisarPorId(Integer.parseInt(d_id)));
                        }
                        turmaInclusao.setDisciplinas(listaDisciplinas);
                    }

                    dao.incluir(turmaInclusao);

                    response.sendRedirect("TurmaController?acao=listagem");
                    return;

                } catch (Exception error) {
                    error.printStackTrace();
                    response.sendRedirect("erroDeExcecao.html");
                }
                break;

            case "formularioEdicao":
                Turma turmaForm = dao.pesquisarPorId(id);
                request.setAttribute("turma", turmaForm);
                request.setAttribute("alunos", alunoDao.listar());
                request.setAttribute("disciplinas", disciplinaDao.listar());
                rd = request.getRequestDispatcher("/templates/turma/edicao.jsp");
                rd.forward(request, response);
                return;

            case "edicao":
                try {

                    Turma turmaEdicao = dao.pesquisarPorId(id);

                    turmaEdicao.setCod_turma(codTurma);

                    // alunos
                    Collection<Aluno> listaAlunos = new ArrayList<>();
                    if (alunosId != null) {
                        for (String a_id : alunosId) {
                            listaAlunos.add(alunoDao.pesquisarPorId(Integer.parseInt(a_id)));
                        }
                    }
                    turmaEdicao.setAlunos(listaAlunos);

                    // disciplinas
                    Collection<Disciplina> listaDisciplinas = new ArrayList<>();
                    if (disciplinasId != null) {
                        for (String d_id : disciplinasId) {
                            listaDisciplinas.add(disciplinaDao.pesquisarPorId(Integer.parseInt(d_id)));
                        }
                    }
                    turmaEdicao.setDisciplinas(listaDisciplinas);

                    dao.editar(turmaEdicao);

                    response.sendRedirect("TurmaController?acao=listagem");
                    return;

                } catch (Exception error) {
                    error.printStackTrace(); // 👈 importante
                    response.sendRedirect("erroDeExcecao.html");
                }
                break;

            case "exclusao":
                try {
                    dao.excluir(id);
                    response.sendRedirect("TurmaController?acao=listagem");
                    return;
                } catch (Exception error) {
                    response.sendRedirect("erroDeExcecao.html");
                }
                break;

            case "listagem":
                List<Turma> lista = dao.listar();
                request.setAttribute("turmas", lista);
                rd = request.getRequestDispatcher("/templates/turma/listagem.jsp");
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
