<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Professor"%>
<!DOCTYPE html>
<%
    List<Professor> professores = (List<Professor>) request.getAttribute("professores");
%>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Professores | Acadêmico</title>

    <link rel="stylesheet" href="/Trabalho_POO2_Web/style/global.css">
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet">
    <script src="/Trabalho_POO2_Web/js/exclusao.js"></script>
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
                <li class="menu-item active">
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
            <img src="../../../images/logo-iff.png" alt="Logo Instituto Federal Fluminense">
        </span>
    </header>

    <main>
        <div>
            <h2 class="page-title">Professores</h2>
            <a href="/Trabalho_POO2_Web/templates/professor/cadastro.jsp" class="action-button">
                <span class="material-symbols-outlined">add</span>
                Cadastrar
            </a>
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Matrícula</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <% for (Professor p : professores) {%>
                    <tr>
                        <td>
                            <span>
                                <span class="material-symbols-outlined">person_apron</span>
                                <%= p.getNome()%>
                            </span>
                        </td>
                        <td>
                            <span>
                                <%= p.getMatricula()%>
                            </span>
                        </td>
                        <td>
                            <span>
                                <a href="ProfessorController?acao=formularioEdicao&id=<%= p.getId()%>" class="default-button">
                                    <span class="material-symbols-outlined">edit</span>
                                    Editar
                                </a>
                                <button type="button" onclick="confirmarExclusao('ProfessorController?acao=exclusao&id=<%= p.getId()%>')" class="delete-button">
                                    <span class="material-symbols-outlined">delete</span>
                                    Excluir
                                </button>
                            </span>
                        </td>
                    </tr>
                <% }%>
            </tbody>
        </table>
    </main>

</body>
</html>