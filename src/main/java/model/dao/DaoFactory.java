package model.dao;

public class DaoFactory {
    
    public static AlunoDaoJpa novoAlunoDao(){
        return new AlunoDaoJpa();
    }
    
    public static ProfessorDaoJpa novoProfessorDao(){
        return new ProfessorDaoJpa();
    }
    
    public static TurmaDaoJpa novaTurmaDao(){
        return new TurmaDaoJpa();
    }
    
    public static CursoDaoJpa novoCursoDao(){
        return new CursoDaoJpa();
    }
    
    public static DisciplinaDaoJpa novaDisciplinaDao(){
        return new DisciplinaDaoJpa();
    }
    
}
