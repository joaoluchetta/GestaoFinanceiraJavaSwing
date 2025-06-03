package org.example.src.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoCategoria tipo;
    private String descricao;

    public enum TipoCategoria {
        RECEITA,
        DESPESA
    }

    public Categoria(String nome, TipoCategoria tipo, String descricao) {
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoCategoria getTipo() {
        return tipo;
    }

    public void setTipo(TipoCategoria tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isReceita() {
        return tipo == TipoCategoria.RECEITA;
    }

    public boolean isDespesa() {
        return tipo == TipoCategoria.DESPESA;
    }

    @Override
    public String toString() {
        return nome;
    }

}