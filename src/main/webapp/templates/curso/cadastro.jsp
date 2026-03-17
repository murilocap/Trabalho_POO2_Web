<%@page import="java.util.List"%>
<%@page import="model.Professor"%>
<%@page import="model.Turma"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Professor> professores = (List<Professor>) request.getAttribute("professores");
    List<Turma> turmas = (List<Turma>) request.getAttribute("turmas");
%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastrar Curso | Acadêmico</title>

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
                <li class="menu-item active">
                    <a href="/Trabalho_POO2_Web/CursoController?acao=listagem">
                        <span class="material-symbols-outlined">school</span>
                        Cursos
                    </a>
                </li>
                <li class="menu-item">
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
        <h2 class="page-title">Cadastrar Curso</h2>
    </div>

    <form action="/Trabalho_POO2_Web/CursoController" method="post" class="register">

        <input type="hidden" name="acao" value="inclusao">

        <!-- NOME DO CURSO -->

        <fieldset>
            <label for="nome">
                <span class="material-symbols-outlined">school</span>
                <span>Nome do Curso</span>
            </label>

            <div class="input">
                <input type="text" name="nome" id="nome">
            </div>
        </fieldset>

        <!-- COORDENADOR -->

        <fieldset>
            <label for="coordenador">
                <span class="material-symbols-outlined">person_apron</span>
                <span>Coordenador</span>
            </label>

            <div class="input">

                <select name="coordenadorId" id="coordenador">

                    <% for (Professor p : professores) { %>

                    <option value="<%= p.getId() %>">
                        <%= p.getNome() %>
                    </option>

                    <% } %>

                </select>

            </div>
        </fieldset>

        <!-- COR DO CURSO -->

        <fieldset>
            <label for="cor">
                <span class="material-symbols-outlined">palette</span>
                <span>Cor do Curso</span>
            </label>

            <div class="input">
                <input type="color" name="cor" id="cor">
            </div>
        </fieldset>

        <!-- TURMAS -->

        <fieldset>

            <label>
                <span class="material-symbols-outlined">groups</span>
                <span>Turmas do Curso</span>
            </label>

            <div class="input">

                <% for (Turma t : turmas) { %>

                <label style="display:block">

                    <input type="checkbox"
                           name="turmasId"
                           value="<%= t.getId() %>">

                    <%= t.getCod_turma() %>

                </label>

                <% } %>

            </div>

        </fieldset>

        <fieldset>

            <button class="action-button" type="submit">
                <span class="material-symbols-outlined">add</span>
                Cadastrar
            </button>

            <a class="cancel-button" href="/Trabalho_POO2_Web/templates/curso/listagem.jsp">
                <span class="material-symbols-outlined">chevron_left</span>
                Cancelar
            </a>

        </fieldset>

    </form>

</main>

</body>
</html>