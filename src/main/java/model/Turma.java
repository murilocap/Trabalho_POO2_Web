/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import java.util.Collection;

/**
 *
 * @author isaac
 */
@Entity
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cod_turma;
    
    //Cria a tabela intermediária = Turma_Aluno
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinTable(
            name = "Turma_Aluno",
            joinColumns = @JoinColumn(name = "Turma_id"),
            inverseJoinColumns = @JoinColumn(name = "alunos_id")
    )
    private Collection<Aluno> alunos;
    
    //Cria a tabela intermediária = Turma_Disciplina
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinTable(
            name = "Turma_Disciplina",
            joinColumns = @JoinColumn(name = "Turma_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private Collection<Disciplina> disciplinas;
    
    
    
    public Turma() {
    }

    public Turma(String cod_turma) {
        this.cod_turma = cod_turma;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCod_turma() {
        return cod_turma;
    }

    public void setCod_turma(String cod_turma) {
        this.cod_turma = cod_turma;
    }

    public Collection<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Collection<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Collection<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Collection<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    
    
    
}
