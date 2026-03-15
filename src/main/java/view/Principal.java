/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package view;

import java.util.List;
import model.Aluno;
import model.Curso;
import model.Disciplina;
import model.Professor;
import model.Turma;
import model.dao.AlunoDaoJpa;
import model.dao.DaoFactory;

/**
 *
 * @author isaac
 */
public class Principal {

    public static void main(String[] args) throws Exception {
        
        Professor prof1 = new Professor("Eduardo", "dudu", "2008005");
        
        Professor prof2 = new Professor("John", "Santos", "1968043");
        
        Curso curso1 = new Curso("S.I", prof1, "Azul");
        Disciplina discplina1 = new Disciplina("Banco de Dados I", prof2);
        
        Turma turma1 = new Turma("SI001");
        
        Aluno a1 = new Aluno("Fulano", "da Silva", "2025001");
        Aluno a2 = new Aluno("Jose", "Ciclano", "2025002");
        Aluno a3 = new Aluno("Beltrano", "de Mattos", "2025003");
        Aluno a4 = new Aluno("Cleiton", "Fuba", "2025004");
        Aluno a5 = new Aluno("Giorgian", "de Arrascaeta", "2025005");
        
        AlunoDaoJpa dao = DaoFactory.novoAlunoDao();
        dao.incluir(a4);
        
        //Editar
        //a4.setId(4);
        //dao.editar(a4);
        
        //Excluir
        //Aluno a6 = new Aluno();
        //a6.setId(5);
        //dao.excluir(a6);
        
        List<Aluno> lista = dao.listar();
        for (Aluno a : lista) {
            System.out.println(a.getNome() + " " +a.getSobrenome());
        } 
    }
    
    
}
