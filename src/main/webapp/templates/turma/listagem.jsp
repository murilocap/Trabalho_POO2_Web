<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>
<%@page import="model.Turma"%>
<%@page import="model.Aluno"%>
<%@page import="model.Disciplina"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    <%
        List<Turma> turmas = (List<Turma>) request.getAttribute("turmas");
    %>
    Collection<Disciplina> disciplinas = turma.getDisciplinas();
    Collection<Aluno> alunos = turma.getAlunos();
%>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Turmas | Acadêmico</title>

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
                    <a href="/Trabalho_POO2_Web/templates/curso/listagem.jsp">
                        <span class="material-symbols-outlined">school</span>
                        Cursos
                    </a>
                </li>
                <li class="menu-item active">
                    <a href="/Trabalho_POO2_Web/templates/turma/listagem.jsp">
                        <span class="material-symbols-outlined">groups</span>
                        Turmas
                    </a>
                </li>
                <li class="menu-item">
                    <a href="/Trabalho_POO2_Web/templates/disciplina/listagem.jsp">
                        <span class="material-symbols-outlined">menu_book</span>
                        Disciplinas
                    </a>
                </li>
                <li class="menu-item">
                    <a href="/Trabalho_POO2_Web/templates/professor/listagem.jsp">
                        <span class="material-symbols-outlined">person_apron</span>
                        Professores
                    </a>
                </li>
                <li class="menu-item">
                    <a href="/Trabalho_POO2_Web/templates/aluno/listagem.jsp">
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
            <h2 class="page-title">Turmas</h2>
            <a href="#" class="action-button">
                <span class="material-symbols-outlined">add</span>
                Cadastrar
            </a>
        </div>
        <ul class="main-list">
            <% for (Turma t : turmas) { %>
                <li class="list-item">
                    <div>
                        <!-- ICON -->
                        <div>
                            <span class="material-symbols-outlined">groups</span>
                        </div>
                        <!-- NAME -->
                        <div>
                            <span>Nome da Turma</span>
                        </div>
                    </div>
                    <!-- DISCIPLINAS -->
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Professor</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Disciplina d : disciplinas) {%>
                                <tr>
                                    <td>
                                        <span>
                                            <span class="material-symbols-outlined">menu_book</span>
                                            <%= d.getNome()%>
                                        </span>
                                    </td>
                                    <td>
                                        <span>
                                            <%= d.getProfessor().getNome()%>
                                        </span>
                                    </td>
                                    <td>
                                        <span>
                                            <a href="DisciplinaController?acao=edicao&id=<%= d.getId()%>" class="default-button">
                                                <span class="material-symbols-outlined">edit</span>
                                                Editar
                                            </a>
                                            <a href="DisciplinaController?acao=exclusao&id=<%= d.getId()%>" class="delete-button">
                                                <span class="material-symbols-outlined">delete</span>
                                                Excluir
                                            </a>
                                        </span>
                                    </td>
                                </tr>
                            <% }%>
                        </tbody>
                    </table>
                    <!-- ALUNOS -->
                    <table class="table">
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
                </li>
            <% }%>
        </ul>
    </main>

</body>
</html>