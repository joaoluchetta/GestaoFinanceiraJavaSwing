package org.example.src.model;

import java.util.List;

public interface CategoriaDAO {
    List<Categoria> listarReceitas();
    List<Categoria> listarDespesas();
    void adicionar(Categoria categoria);
    boolean removerPorNome(String nome);
    Categoria buscarPorNome(String nome);
    boolean existeCategoria(String nome);
    String[] getNomesCategorias(Categoria.TipoCategoria tipo);
}
