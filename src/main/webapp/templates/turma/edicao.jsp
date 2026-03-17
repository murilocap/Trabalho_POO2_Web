<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Turma"%>
<%@page import="model.Disciplina"%>
<%@page import="model.Aluno"%>
<!DOCTYPE html>
<%
    Turma turma = (Turma) request.getAttribute("turma");
    List<Aluno> alunos = (List<Aluno>) request.getAttribute("alunos");
    List<Disciplina> disciplinas = (List<Disciplina>) request.getAttribute("disciplinas");
%>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Turma | Acadêmico</title>

    <link rel="stylesheet" href="/Trabalho_POO2_Web/style/global.css">
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet">
</head>
<body>

<header>
    <span>
        <p class="logo">acadêmico</p>
    </span>

    <nav>
            <ul class="menu">
                <li class="menu-item">
                    <a href="/Trabalho_POO2_Web/CursoController?acao=listagem">
                        <span class="material-symbols-outlined">school</span>
                        Cursos
                    </a>
                </li>
                <li class="menu-item active">
                    <a href="/Trabalho_POO2_Web/TurmaController?acao=listagem">
                        <span class="material-symbols-outlined">groups</span>
                        Turmas
                    </a>
                </li>
                <li class="menu-item">
                    <a href="/Trabalho_POO2_Web/DisciplinaController?acao=listagem">
                        <span class="material-symbols-outlined">menu_book</span>
                        Disciplinas
                    </a>
                </li>
                <li class="menu-item">
                    <a href="/Trabalho_POO2_Web/ProfessorController?acao=listagem">
                        <span class="material-symbols-outlined">person_apron</span>
                        Professores
                    </a>
                </li>
                <li class="menu-item">
                    <a href="/Trabalho_POO2_Web/AlunoController?acao=listagem">
                        <span class="material-symbols-outlined">person_edit</span>
                        Alunos
                    </a>
                </li>
            </ul>
        </nav>

    <span class="iff-logo">
        <img src="/Trabalho_POO2_Web/images/logo-iff.png" alt="Logo Instituto Federal Fluminense">
    </span>
</header>

<main>

    <div>
        <h2 class="page-title">Editar Turma</h2>
    </div>

    <form action="/Trabalho_POO2_Web/TurmaController" method="post" class="register">

        <input type="hidden" name="acao" value="edicao">
        <input type="hidden" name="id" value="<%= turma.getId() %>">

        <fieldset>
            <label for="cod_turma">
                <span class="material-symbols-outlined">groups</span>
                <span>Código da Turma</span>
            </label>
            <div class="input">
                <input
                        type="text"
                        name="cod_turma"
                        id="cod_turma"
                        value="<%= turma.getCod_turma() %>"
                >
            </div>
        </fieldset>

        <!-- DISCIPLINAS -->
        <fieldset>
            <label>
                <span class="material-symbols-outlined">menu_book</span>
                <span>Disciplinas</span>
            </label>
            <div class="input" style="display: flex; flex-direction: column; gap: 10px;">

                <% for (Disciplina d : disciplinas) {

                    boolean checked = false;

                    if (turma.getDisciplinas() != null) {
                        for (Disciplina td : turma.getDisciplinas()) {
                            if (td.getId() == d.getId()) {
                                checked = true;
                                break;
                            }
                        }
                    }
                %>

                <label style="display:block">

                    <input
                            type="checkbox"
                            name="disciplinasId"
                            value="<%= d.getId() %>"
                        <%= checked ? "checked" : "" %>
                    >

                    <%= d.getNome() %> - <%= d.getProfessor().getNome() %>

                </label>

                <% } %>

            </div>
        </fieldset>

        <!-- ALUNOS -->
        <fieldset>
            <label>
                <span class="material-symbols-outlined">person_edit</span>
                <span>Alunos</span>
            </label>

            <div class="input" style="display: flex; flex-direction: column; gap: 10px;">

                <% for (Aluno a : alunos) {

                    boolean checked = false;

                    if (turma.getAlunos() != null) {
                        for (Aluno ta : turma.getAlunos()) {
                            if (ta.getId() == a.getId()) {
                                checked = true;
                                break;
                            }
                        }
                    }
                %>

                <label style="display:block">

                    <input
                            type="checkbox"
                            name="alunosId"
                            value="<%= a.getId() %>"
                        <%= checked ? "checked" : "" %>
                    >

                    <%= a.getNome() %> - <%= a.getMatricula() %>

                </label>

                <% } %>

            </div>

        </fieldset>

        <fieldset>
            <button class="action-button" type="submit">
                <span class="material-symbols-outlined">check_small</span>
                Salvar
            </button>

            <a class="cancel-button" href="/Trabalho_POO2_Web/TurmaController?acao=listagem">
                <span class="material-symbols-outlined">chevron_left</span>
                Cancelar
            </a>
        </fieldset>

    </form>

</main>

</body>
</html>