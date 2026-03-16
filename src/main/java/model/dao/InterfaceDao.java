package model.dao;

import java.util.List;

public interface InterfaceDao<T> {

    public abstract void incluir(T entidade) throws Exception;
    
    public abstract void editar(T entidade) throws Exception;
    
    public abstract void excluir(int id) throws Exception;

    public abstract List<T> listar() throws Exception;
    
}
