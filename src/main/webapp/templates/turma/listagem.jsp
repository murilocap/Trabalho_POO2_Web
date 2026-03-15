<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cursos | Acadêmico</title>

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
            <li>
                <a href="#" class="list-item">
                    <!-- ICON -->
                    <div>
                        <span class="material-symbols-outlined">groups</span>
                    </div>
                    <!-- NAME -->
                    <div>
                        <span>Nome da Turma</span>
                    </div>
                </a>
            </li>
            <li>
                <a href="#" class="list-item">
                    <!-- ICON -->
                    <div>
                        <span class="material-symbols-outlined">groups</span>
                    </div>
                    <!-- NAME -->
                    <div>
                        <span>Nome da Turma</span>
                    </div>
                </a>
            </li>
            <li>
                <a href="#" class="list-item">
                    <!-- ICON -->
                    <div>
                        <span class="material-symbols-outlined">groups</span>
                    </div>
                    <!-- NAME -->
                    <div>
                        <span>Nome da Turma</span>
                    </div>
                </a>
            </li>
        </ul>
    </main>

</body>
</html>