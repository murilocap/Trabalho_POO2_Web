<%-- 
    Document   : cadastro
    Created on : 15 de mar. de 2026, 13:03:15
    Author     : isaac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <% 
        String acao = request.getParameter("acao");
    %>
<html lang="pt-br">
    
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastrar Aluno | Acadêmico</title>

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
                <li class="menu-item">
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
                <li class="menu-item active">
                    <a href="/Trabalho_POO2_Web/templates/aluno/listagem.jsp">
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
            <h2 class="page-title">Cadastrar Aluno</h2>
        </div>
        <form action="/Trabalho_POO2_Web/AlunoController" method="post" class="register">
            <input type="hidden" name="acao" value="inclusao">
            <fieldset>
                <label for="matricula">
                    <span class="material-symbols-outlined">id_card</span>
                    <span>Matrícula</span>
                </label>
                <div class="input">
                    <input type="text" name="matricula" id="matricula">
                </div>
            </fieldset>
            <fieldset>
                <label for="nome">
                    <span class="material-symbols-outlined">person</span>
                    <span>Nome</span>
                </label>
                <div class="input">
                    <input type="text" name="nome" id="nome">
                </div>
            </fieldset>
            <fieldset>
                <button class="action-button" type="submit">
                    <span class="material-symbols-outlined">check_small</span>
                    Salvar
                </button>
                <a class="cancel-button" href="/Trabalho_POO2_Web/templates/aluno/listagem.jsp">
                    <span class="material-symbols-outlined">chevron_left</span>
                    Cancelar
                </a>
            </fieldset>
        </form>
    </main>

</body>
</html>
