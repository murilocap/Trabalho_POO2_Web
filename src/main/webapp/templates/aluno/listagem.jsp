<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Aluno"%>

<!DOCTYPE html>
<%
    List<Aluno> alunos = (List<Aluno>) request.getAttribute("alunos");
%>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Alunos | Acadêmico</title>

        <link rel="stylesheet" href="../../style/global.css">
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
                <li class="menu-item active">
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
                <h2 class="page-title">Alunos</h2>
                <a href="/Trabalho_POO2_Web/templates/aluno/cadastro.jsp" class="action-button">
                    <span class="material-symbols-outlined">add</span>
                    Cadastrar
                </a>
            </div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Matrícula</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Aluno a : alunos) {%>
                        <tr>
                            <td>
                                <span>
                                    <span class="material-symbols-outlined">person_edit</span>
                                    <%= a.getNome()%>
                                </span>
                            </td>
                            <td>
                                <span>
                                    <%= a.getMatricula()%>
                                </span>
                            </td>
                            <td>
                                <span>
                                    <a href="AlunoController?acao=edicao&id=<%= a.getId()%>" class="default-button">
                                        <span class="material-symbols-outlined">edit</span>
                                        Editar
                                    </a>
                                    <a href="AlunoController?acao=exclusao&id=<%= a.getId()%>" class="delete-button">
                                        <span class="material-symbols-outlined">delete</span>
                                        Excluir
                                    </a>
                                </span>
                            </td>
                        </tr>
                    <% }%>
                </tbody>
            </table>
        </main>

    </body>
</html>