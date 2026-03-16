package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Curso;
import model.Professor;
import model.Turma;
import model.dao.CursoDaoJpa;
import model.dao.DaoFactory;
import model.dao.ProfessorDaoJpa;
import model.dao.TurmaDaoJpa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CursoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        response.setContentType("text/html;charset=UTF-8");

        String acao = request.getParameter("acao");
        String idParam = request.getParameter("id");

        String nome = request.getParameter("nome");
        String cor = request.getParameter("cor");
        String coordenadorId = request.getParameter("coordenadorId");

        String[] turmasId = request.getParameterValues("turmasId");

        CursoDaoJpa dao = DaoFactory.novoCursoDao();
        ProfessorDaoJpa professorDao = DaoFactory.novoProfessorDao();
        TurmaDaoJpa turmaDao = DaoFactory.novaTurmaDao();

        RequestDispatcher rd = null;

        switch (acao) {

            case "inclusao":

                try {

                    Curso curso = new Curso();

                    curso.setNome(nome);
                    curso.setCor(cor);

                    // Coordenador
                    if (coordenadorId != null) {
                        Professor coordenador = professorDao.pesquisarPorId(Integer.parseInt(coordenadorId));
                        curso.setCoordenador(coordenador);
                    }

                    // Turmas
                    if (turmasId != null) {

                        Collection<Turma> turmas = new ArrayList<>();

                        for (String tid : turmasId) {

                            Turma t = turmaDao.pesquisarPorId(Integer.parseInt(tid));
                            turmas.add(t);

                        }

                        curso.setTurmas(turmas);
                    }

                    if (idParam == null || idParam.isEmpty()) {

                        dao.incluir(curso);

                    } else {

                        curso.setId(Integer.parseInt(idParam));
                        dao.editar(curso);

                    }

                    response.sendRedirect("CursoController?acao=listagem");
                    return;

                } catch (Exception error) {

                    response.sendRedirect("erroDeExcecao.html");

                }

                break;

            case "edicao":

                Curso curso = dao.pesquisarPorId(Integer.parseInt(idParam));

                request.setAttribute("curso", curso);

                request.setAttribute("professores", professorDao.listar());
                request.setAttribute("turmas", turmaDao.listar());

                rd = request.getRequestDispatcher("/templates/curso/cadastro.jsp");
                rd.forward(request, response);

                return;

            case "exclusao":

                try {

                    dao.excluir(Integer.parseInt(idParam));

                    response.sendRedirect("CursoController?acao=listagem");
                    return;

                } catch (Exception error) {

                    response.sendRedirect("erroDeExcecao.html");

                }

                break;

            case "listagem":

                List<Curso> lista = dao.listar();

                request.setAttribute("cursos", lista);

                rd = request.getRequestDispatcher("/templates/curso/listagem.jsp");
                rd.forward(request, response);

                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}