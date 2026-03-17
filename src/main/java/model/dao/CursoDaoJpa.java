package model.dao;

import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import model.Curso;

public class CursoDaoJpa implements InterfaceDao<Curso>{

    @Override
     public void incluir(Curso entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(entidade);
            em.getTransaction().commit();
        } finally{
            em.close();
        }
    }

    @Override
    public void editar(Curso entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(entidade);
            em.getTransaction().commit();
        } finally{
            em.close();
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
       try{
           em.getTransaction().begin();
           Curso c1 = em.find(Curso.class, id);
           em.remove(c1);
           em.getTransaction().commit();
       } finally{
           em.close();
       }
    }

    @Override
    public List<Curso> listar() throws Exception {
        List<Curso> listaCursos = new ArrayList();
        EntityManager em = ConnFactory.getEntityManager();
        try{
            em.getTransaction().begin();
            listaCursos = em.createQuery("FROM Curso c").getResultList();
            em.getTransaction().commit();
        } finally{
            em.close();
        }
         return listaCursos;
    }

    public Curso pesquisarPorId(int id) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            return em.find(Curso.class, id);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar curso por ID", e);
        }
    }
}
