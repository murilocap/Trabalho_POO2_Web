package model.dao;

import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;
import model.Professor;
import model.Turma;

public class TurmaDaoJpa implements InterfaceDao<Turma> {

    @Override
    public void incluir(Turma entidade) throws Exception {
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
    public void editar(Turma entidade) throws Exception {
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
           Turma t1 = em.find(Turma.class, id);
           em.remove(t1);
           em.getTransaction().commit();
       } finally{
           em.close();
       }
    }
    
    @Override
    public List<Turma> listar() throws Exception {
       List<Turma> listaTurmas = new ArrayList();
        EntityManager em = ConnFactory.getEntityManager();
        try{
            em.getTransaction().begin();
            listaTurmas = em.createQuery("FROM Turma t").getResultList();
            em.getTransaction().commit();
        } finally{
            em.close();
        }
         return listaTurmas;
    }
    
    public void excluirTurmaComAlunos(int id) throws Exception{
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            Turma turma = em.find(Turma.class, id);
            if (turma != null) {
                em.remove(turma);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Turma pesquisarPorId(int id) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            return em.find(Turma.class, id);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar turma por ID", e);
        }
    }
    
}
