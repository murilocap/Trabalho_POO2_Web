<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cadastrar Disciplina | Acadêmico</title>

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
                    <a href="/Trabalho_POO2_Web/TurmaController?acao=listagem">
                        <span class="material-symbols-outlined">groups</span>
                        Turmas
                    </a>
                </li>
                <li class="menu-item active">
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
                <h2 class="page-title">Cadastrar Disciplina</h2>
            </div>
            <form action="/Trabalho_POO2_Web/DisciplinaController" method="post" class="register">
                <input type="hidden" name="acao" value="inclusao">
                <fieldset>
                    <label for="nome">
                        <span class="material-symbols-outlined">menu_book</span>
                        <span>Nome da Disciplina</span>
                    </label>
                    <div class="input">
                        <input type="text" name="nome" id="nome">
                    </div>
                </fieldset>
                <fieldset>
                    <label for="professor">
                        <span class="material-symbols-outlined">person_apron</span>
                        <span>Professor da Disciplina</span>
                    </label>
                    <div class="input">
                        <select name="professorId" id="professor">

                            <c:forEach var="professor" items="${professores}">
                            <option value="${professor.id}">
                                ${professor.nome}
                            </option>
                            </c:forEach>

                        </select>
                    </div>
                </fieldset>
                <fieldset>
                    <button class="action-button" type="submit">
                        <span class="material-symbols-outlined">check_small</span>
                        Salvar
                    </button>
                    <a class="cancel-button" href="/Trabalho_POO2_Web/DisciplinaController?acao=listagem">
                        <span class="material-symbols-outlined">chevron_left</span>
                        Cancelar
                    </a>
                </fieldset>
            </form>
        </main>

    </body>
</html>
