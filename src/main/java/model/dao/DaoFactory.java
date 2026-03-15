package model.dao;

public class DaoFactory {
    
    public static AlunoDaoJpa novoAlunoDao(){
        return new AlunoDaoJpa();
    }
    
}
