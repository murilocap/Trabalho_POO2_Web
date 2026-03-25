<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Turmas | Acadêmico</title>

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
            <h2 class="page-title">Turmas</h2>
            <a href="/Trabalho_POO2_Web/TurmaController?acao=formularioInclusao" class="action-button">
                <span class="material-symbols-outlined">add</span>
                Cadastrar
            </a>
        </div>
        <ul class="main-list">
            <c:forEach var="turma" items="${turmas}">
                <li class="list-item" style="flex-direction: column; align-items: flex-start;">
                    <div>
                        <!-- ICON -->
                        <div>
                            <span class="material-symbols-outlined">groups</span>
                        </div>
                        <!-- NAME -->
                        <div>
                            <span>${turma.cod_turma}</span>
                        </div>
                        <!-- ACTIONS -->
                        <div style="margin-left: 40px;">
                            <span>
                                <a href="TurmaController?acao=formularioEdicao&id=${turma.id}" class="default-button">
                                    <span class="material-symbols-outlined">edit</span>
                                    Editar
                                </a>
                                <button type="button" onclick="confirmarExclusao('TurmaController?acao=exclusao&id=${turma.id}')" class="delete-button">
                                    <span class="material-symbols-outlined">delete</span>
                                    Excluir
                                </button>
                            </span>
                        </div>
                    </div>
                    <!-- DISCIPLINAS -->
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Disciplina</th>
                                <th>Professor</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="disciplina" items="${turma.disciplinas}">
                                <tr>
                                    <td>
                                        <span>
                                            <span class="material-symbols-outlined">menu_book</span>
                                            ${disciplina.nome}
                                        </span>
                                    </td>
                                    <td>
                                        <span>
                                            ${disciplina.professor.nome}
                                        </span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <!-- ALUNOS -->
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Alunos</th>
                                <th>Matrícula</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="aluno" items="${turma.alunos}">
                                <tr>
                                    <td>
                                        <span>
                                            <span class="material-symbols-outlined">person_edit</span>
                                            ${aluno.nome}
                                        </span>
                                    </td>
                                    <td>
                                        <span>
                                            ${aluno.matricula}
                                        </span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </li>
            </c:forEach>
        </ul>
    </main>

</body>
</html>