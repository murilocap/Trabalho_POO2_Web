/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import model.Disciplina;

/**
 *
 * @author isaac
 */
public class DisciplinaDaoJpa implements InterfaceDao<Disciplina> {

    @Override
     public void incluir(Disciplina entidade) throws Exception {
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
    public void editar(Disciplina entidade) throws Exception {
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
    public void excluir(Disciplina entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
       try{
           em.getTransaction().begin();
           Disciplina d1 = em.find(Disciplina.class, entidade.getId());
           em.remove(d1);
           em.getTransaction().commit();
       } finally{
           em.close();
       }
    }

    @Override
    public List<Disciplina> listar() throws Exception {
        List<Disciplina> listaDisciplinas = new ArrayList();
        EntityManager em = ConnFactory.getEntityManager();
        try{
            em.getTransaction().begin();
            listaDisciplinas = em.createQuery("FROM Disciplina d").getResultList();
            em.getTransaction().commit();
        } finally{
            em.close();
        }
         return listaDisciplinas;
    }
    
    
    
}
