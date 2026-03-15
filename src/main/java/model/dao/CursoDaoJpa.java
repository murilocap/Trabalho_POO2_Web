/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import model.Curso;

/**
 *
 * @author isaac
 */
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
    public void excluir(Curso entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
       try{
           em.getTransaction().begin();
           Curso c1 = em.find(Curso.class, entidade.getId());
           em.remove(c1);
           em.getTransaction().commit();
       } finally{
           em.close();
       }
    }

    @Override
    public Curso pesquisarPorId(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    
}
