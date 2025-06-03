package org.example.src.controller;

import org.example.src.model.Categoria;
import org.example.src.model.CategoriaDAO;
import org.example.src.model.CategoriaDAOImpl;
import org.example.src.view.CategoriaScreen;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CategoriaController {

    private final CategoriaScreen view;
    private final CategoriaDAO categoriaDAO;
    private String categoriaEmEdicao = null;

    public CategoriaController() {
        this.view = new CategoriaScreen();
        this.categoriaDAO = new CategoriaDAOImpl();

        carregarListas();

        view.setAdicionarListener(e -> salvarCategoria());
        view.setEditarListener(e -> prepararEdicao());
        view.setRemoverListener(e -> removerCategoria());
        view.setVoltarListener(e -> view.dispose());

        view.setDoubleClickReceita(doubleClickListenerReceita());
        view.setDoubleClickDespesa(doubleClickListenerDespesa());
    }

    private void carregarListas() {
        List<Categoria> receitas = categoriaDAO.listarReceitas();
        List<Categoria> despesas = categoriaDAO.listarDespesas();
        view.setListaReceitas(receitas);
        view.setListaDespesas(despesas);
    }

    private void salvarCategoria() {
        String nome = view.getNome();
        String descricao = view.getDescricao();
        Categoria.TipoCategoria tipo = view.getTipoSelecionado();

        if (nome.isEmpty() || descricao.isEmpty()) {
            view.showDialog("Preencha todos os campos.");
            return;
        }

        try {
            Categoria categoria = new Categoria(nome, tipo, descricao);

            if (categoriaEmEdicao == null) {
                if (categoriaDAO.existeCategoria(nome)) {
                    view.showDialog("Categoria com esse nome já existe.");
                    return;
                }
                categoriaDAO.adicionar(categoria);
                view.showSuccess("Categoria adicionada com sucesso!");
            } else {
                Categoria existente = categoriaDAO.buscarPorNome(categoriaEmEdicao);
                if (existente != null) {
                    existente.setNome(nome);
                    existente.setDescricao(descricao);
                    existente.setTipo(tipo);
                    categoriaDAO.adicionar(existente); // Hibernate update (saveOrUpdate)
                    view.showSuccess("Categoria atualizada com sucesso!");
                }
                categoriaEmEdicao = null;
            }

            view.limparCampos();
            carregarListas();

        } catch (Exception ex) {
            view.showDialog("Erro: " + ex.getMessage());
        }
    }

    private void prepararEdicao() {
        String nomeReceita = view.getCategoriaReceitaSelecionada();
        String nomeDespesa = view.getCategoriaDespesaSelecionada();

        String nome = nomeReceita != null ? nomeReceita : nomeDespesa;
        if (nome == null) {
            view.showDialog("Selecione uma categoria para editar.");
            return;
        }

        Categoria categoria = categoriaDAO.buscarPorNome(nome);
        if (categoria != null) {
            view.setCampos(categoria.getNome(), categoria.getDescricao(), categoria.getTipo());
            categoriaEmEdicao = categoria.getNome();
        }
    }

    private void removerCategoria() {
        String nomeReceita = view.getCategoriaReceitaSelecionada();
        String nomeDespesa = view.getCategoriaDespesaSelecionada();

        String nome = nomeReceita != null ? nomeReceita : nomeDespesa;
        if (nome == null) {
            view.showDialog("Selecione uma categoria para remover.");
            return;
        }

        if (view.confirmarRemocao(nome)) {
            if (categoriaDAO.removerPorNome(nome)) {
                view.showSuccess("Categoria removida com sucesso!");
                carregarListas();
            } else {
                view.showDialog("Erro ao remover a categoria.");
            }
        }
    }

    private MouseAdapter doubleClickListenerReceita() {
        return new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String nome = view.getCategoriaReceitaSelecionada();
                    Categoria categoria = categoriaDAO.buscarPorNome(nome);
                    if (categoria != null) {
                        view.setCampos(categoria.getNome(), categoria.getDescricao(), categoria.getTipo());
                        categoriaEmEdicao = categoria.getNome();
                    }
                }
            }
        };
    }

    private MouseAdapter doubleClickListenerDespesa() {
        return new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String nome = view.getCategoriaDespesaSelecionada();
                    Categoria categoria = categoriaDAO.buscarPorNome(nome);
                    if (categoria != null) {
                        view.setCampos(categoria.getNome(), categoria.getDescricao(), categoria.getTipo());
                        categoriaEmEdicao = categoria.getNome();
                    }
                }
            }
        };
    }
}
