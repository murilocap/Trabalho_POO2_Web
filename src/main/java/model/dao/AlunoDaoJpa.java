/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;

/**
 *
 * @author isaac
 */
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
    public void excluir(Aluno entidade) throws Exception {
       EntityManager em = ConnFactory.getEntityManager();
       try{
           em.getTransaction().begin();
           Aluno a1 = em.find(Aluno.class, entidade.getId());
           em.remove(a1);
           em.getTransaction().commit();
       } finally{
           em.close();
       }
    }

    @Override
    public Aluno pesquisarPorId(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
   
}
