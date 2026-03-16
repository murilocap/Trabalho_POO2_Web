package model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "coordenador_id")
    private Professor coordenador;
    private String cor;
    
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinTable(
            name = "Curso_Turma",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "turma_id")
    )
    private Collection<Turma> turmas;

    public Curso() {
    }

    public Curso(String nome, Professor coordenador, String cor) {
        this.nome = nome;
        this.coordenador = coordenador;
        this.cor = cor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Professor getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Professor coordenador) {
        this.coordenador = coordenador;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Collection<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(Collection<Turma> turmas) {
        this.turmas = turmas;
    }
    
}