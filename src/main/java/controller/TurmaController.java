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
import model.Professor;
import model.dao.ProfessorDaoJpa;

public class TurmaController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        response.setContentType("text/html;charset=UTF-8");

        String acao = request.getParameter("acao");
        String idParam = request.getParameter("id");

        String codTurma = request.getParameter("cod_turma");

        String[] alunosId = request.getParameterValues("alunosId");
        String[] disciplinasId = request.getParameterValues("disciplinasId");

        TurmaDaoJpa dao = DaoFactory.novaTurmaDao();
        AlunoDaoJpa alunoDao = DaoFactory.novoAlunoDao();
        DisciplinaDaoJpa disciplinaDao = DaoFactory.novaDisciplinaDao();
        ProfessorDaoJpa professorDao = DaoFactory.novoProfessorDao();

        RequestDispatcher rd = null;

        switch (acao) {

            case "inclusao":

                try {

                    Turma turma = new Turma();

                    turma.setCod_turma(codTurma);

                    // Alunos
                    if (alunosId != null) {

                        Collection<Aluno> alunos = new ArrayList<>();

                        for (String aid : alunosId) {

                            Aluno a = alunoDao.pesquisarPorId(Integer.parseInt(aid));
                            alunos.add(a);

                        }

                        turma.setAlunos(alunos);
                    }

                    // Disciplinas
                    if (disciplinasId != null) {

                        Collection<Disciplina> disciplinas = new ArrayList<>();

                        for (String did : disciplinasId) {

                            Disciplina d = disciplinaDao.pesquisarPorId(Integer.parseInt(did));
                            disciplinas.add(d);

                        }

                        turma.setDisciplinas(disciplinas);
                    }

                    if (idParam == null || idParam.isEmpty()) {

                        dao.incluir(turma);

                    } else {

                        turma.setId(Integer.parseInt(idParam));
                        dao.editar(turma);

                    }

                    response.sendRedirect("TurmaController?acao=listagem");
                    return;

                } catch (Exception error) {

                    response.sendRedirect("erroDeExcecao.html");

                }

                break;

            case "edicao":

                Turma turma = dao.pesquisarPorId(Integer.parseInt(idParam));

                request.setAttribute("turma", turma);
                request.setAttribute("alunos", alunoDao.listar());
                request.setAttribute("disciplinas", disciplinaDao.listar());

                rd = request.getRequestDispatcher("/templates/turma/cadastro.jsp");
                rd.forward(request, response);

                return;

            case "exclusao":

                try {

                    dao.excluir(Integer.parseInt(idParam));

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

            case "telaCadastro":

                List<Disciplina> listaDisciplina = disciplinaDao.listar();
                List<Aluno> listaAluno = alunoDao.listar();

                request.setAttribute("disciplinas", listaDisciplina);
                request.setAttribute("alunos", listaAluno);

                rd = request.getRequestDispatcher("/templates/turma/cadastro.jsp");
                rd.forward(request, response);

                break;

            case "excluirAlunoDeTurma":
               

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
