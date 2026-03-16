package model.dao;

import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;

public class AlunoDaoJpa implements InterfaceDao<Aluno>{

    @Override
    public void incluir(Aluno entidade) throws Exception {
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
    public void editar(Aluno entidade) throws Exception {
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
           Aluno a1 = em.find(Aluno.class, id);
           em.remove(a1);
           em.getTransaction().commit();
       } finally{
           em.close();
       }
    }

    @Override
    public List<Aluno> listar() throws Exception {
        List<Aluno> listaAlunos = new ArrayList();
        EntityManager em = ConnFactory.getEntityManager();
        try{
            em.getTransaction().begin();
            listaAlunos = em.createQuery("FROM Aluno a").getResultList();
            em.getTransaction().commit();
        } finally{
            em.close();
        }
         return listaAlunos;
    }

    public Aluno pesquisarPorId(int id) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            return em.find(Aluno.class, id);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar professor por ID", e);
        }
    }
   
}
