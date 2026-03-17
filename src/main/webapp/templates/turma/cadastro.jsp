<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Disciplina"%>
<%@page import="model.Aluno"%>
<%
    List<Aluno> alunos = (List<Aluno>) request.getAttribute("alunos");
    List<Disciplina> disciplinas = (List<Disciplina>) request.getAttribute("disciplinas");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastrar Turma | Acadêmico</title>

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
            <h2 class="page-title">Cadastrar Turma</h2>
        </div>

        <form action="/Trabalho_POO2_Web/TurmaController" method="post" class="register">
            <input type="hidden" name="acao" value="inclusao">
            <fieldset>
                <label for="cod_turma">
                    <span class="material-symbols-outlined">groups</span>
                    <span>Código da Turma</span>
                </label>
                <div class="input">
                    <input type="text" name="cod_turma" id="cod_turma">
                </div>
            </fieldset>
            <fieldset>
                <label>
                    <span class="material-symbols-outlined">menu_book</span>
                    <span>Disciplinas</span>
                </label>
                <div class="input">

                <% for (Disciplina d : disciplinas) { %>

                <label style="display:block">

                <input type="checkbox"
                    name="disciplinasId"
                    value="<%= d.getId() %>">

                <%= d.getNome() %> - <%= d.getProfessor().getNome() %>

                </label>

                <% } %>

                </div>
            </fieldset>
            <fieldset>
            <label>
                <span class="material-symbols-outlined">person_edit</span>
                <span>Alunos</span>
            </label>
            <div class="input">
            <% for (Aluno a : alunos) { %>
            <label style="display:block">
            <input type="checkbox"
                name="alunosId"
                value="<%= a.getId() %>">

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
                <a class="cancel-button" href="TurmaController?acao=listagem">
                    <span class="material-symbols-outlined">chevron_left</span>
                    Cancelar
                </a>
            </fieldset>
        </form>
    </main>

</body>
</html>