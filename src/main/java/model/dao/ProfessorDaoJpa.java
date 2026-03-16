package model.dao;

import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import model.Professor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author isaac
 */
public class ProfessorDaoJpa implements InterfaceDao<Professor>{

    @Override
    public void incluir(Professor entidade) throws Exception {
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
    public void editar(Professor entidade) throws Exception {
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
    public void excluir(Professor entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
       try{
           em.getTransaction().begin();
           Professor a1 = em.find(Professor.class, entidade.getId());
           em.remove(a1);
           em.getTransaction().commit();
       } finally{
           em.close();
       }
    }

    @Override
    public List<Professor> listar() throws Exception {
        List<Professor> listaProfessor = new ArrayList();
        EntityManager em = ConnFactory.getEntityManager();
        try{
            em.getTransaction().begin();
            listaProfessor = em.createQuery("FROM Professor p").getResultList();
            em.getTransaction().commit();
        } finally{
            em.close();
        }
         return listaProfessor;
    }
    
    public Professor pesquisarPorId(int id) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
    try {
        return em.find(Professor.class, id);
    } catch (Exception e) {
        throw new Exception("Erro ao buscar professor por ID", e);
    }
}
    
}
