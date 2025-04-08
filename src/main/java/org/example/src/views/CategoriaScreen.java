package org.example.src.views;

import org.example.src.components.CategoriaManager;
import org.example.src.components.Styles;
import org.example.src.models.Categoria;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CategoriaScreen extends JFrame implements ActionListener {
    private JTextField nomeField;
    private JComboBox<String> tipoCombo;
    private JTextField descricaoField;
    private JList<String> listaReceitas;
    private JList<String> listaDespesas;
    private DefaultListModel<String> modelReceitas;
    private DefaultListModel<String> modelDespesas;
    private JButton adicionarButton;
    private JButton editarButton;
    private JButton removerButton;
    private JButton voltarButton;
    private String categoriaEmEdicao = null;

    public CategoriaScreen() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setTitle("Gerenciador de Categorias");
        
        CategoriaManager.CategoriaPanelComponents categoriaComponents =
                CategoriaManager.createCategoriaPanel();
        
        nomeField = categoriaComponents.nomeField;
        tipoCombo = categoriaComponents.tipoCombo;
        descricaoField = categoriaComponents.descricaoField;
        listaReceitas = categoriaComponents.listaReceitas;
        listaDespesas = categoriaComponents.listaDespesas;
        modelReceitas = categoriaComponents.modelReceitas;
        modelDespesas = categoriaComponents.modelDespesas;
        adicionarButton = categoriaComponents.adicionarButton;
        editarButton = categoriaComponents.editarButton;
        removerButton = categoriaComponents.removerButton;
        voltarButton = categoriaComponents.voltarButton;
        
        adicionarButton.addActionListener(this);
        editarButton.addActionListener(this);
        removerButton.addActionListener(this);
        voltarButton.addActionListener(this);
        
        listaReceitas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    carregarCategoriaSelecionada(listaReceitas.getSelectedValue(), Categoria.TipoCategoria.RECEITA);
                }
            }
        });

        listaDespesas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    carregarCategoriaSelecionada(listaDespesas.getSelectedValue(), Categoria.TipoCategoria.DESPESA);
                }
            }
        });
        
        add(categoriaComponents.panel);
        setSize(600, 500);
        Styles.centerOnScreen(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adicionarButton) {
            adicionarCategoria();
        } else if (e.getSource() == editarButton) {
            editarCategoria();
        } else if (e.getSource() == removerButton) {
            removerCategoriaSelecionada();
        } else if (e.getSource() == voltarButton) {
            dispose();
        }
    }

    private void adicionarCategoria() {
        String nome = nomeField.getText().trim();
        String descricao = descricaoField.getText().trim();
        Categoria.TipoCategoria tipo = (tipoCombo.getSelectedIndex() == 0) ?
                Categoria.TipoCategoria.RECEITA : Categoria.TipoCategoria.DESPESA;
        
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "O nome da categoria é obrigatório.",
                    "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (categoriaEmEdicao != null) {
            Categoria.removerPorNome(categoriaEmEdicao);
            atualizarListasCategorias();
            categoriaEmEdicao = null;
            adicionarButton.setText("Adicionar");
            editarButton.setEnabled(true);
        }
        
        if (Categoria.existeCategoria(nome)) {
            JOptionPane.showMessageDialog(this,
                    "Já existe uma categoria com este nome.",
                    "Categoria duplicada", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Categoria novaCategoria = new Categoria(nome, tipo, descricao);
        Categoria.adicionar(novaCategoria);
        
        atualizarListasCategorias();
        
        limparCampos();

        JOptionPane.showMessageDialog(this,
                "Categoria adicionada com sucesso!",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void editarCategoria() {
        String nomeSelecionado = null;
        Categoria.TipoCategoria tipoSelecionado = null;

        if (listaReceitas.getSelectedValue() != null) {
            nomeSelecionado = listaReceitas.getSelectedValue();
            tipoSelecionado = Categoria.TipoCategoria.RECEITA;
        } else if (listaDespesas.getSelectedValue() != null) {
            nomeSelecionado = listaDespesas.getSelectedValue();
            tipoSelecionado = Categoria.TipoCategoria.DESPESA;
        }

        if (nomeSelecionado == null) {
            JOptionPane.showMessageDialog(this,
                    "Selecione uma categoria para editar.",
                    "Seleção requerida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        carregarCategoriaSelecionada(nomeSelecionado, tipoSelecionado);
    }

    private void removerCategoriaSelecionada() {
        String nomeSelecionado = null;

        if (listaReceitas.getSelectedValue() != null) {
            nomeSelecionado = listaReceitas.getSelectedValue();
        } else if (listaDespesas.getSelectedValue() != null) {
            nomeSelecionado = listaDespesas.getSelectedValue();
        }

        if (nomeSelecionado == null) {
            JOptionPane.showMessageDialog(this,
                    "Selecione uma categoria para remover.",
                    "Seleção requerida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int resposta = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja remover a categoria '" + nomeSelecionado + "'?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (resposta == JOptionPane.YES_OPTION) {
            if (Categoria.removerPorNome(nomeSelecionado)) {
                atualizarListasCategorias();

                JOptionPane.showMessageDialog(this,
                        "Categoria removida com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Erro ao remover categoria.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void carregarCategoriaSelecionada(String nome, Categoria.TipoCategoria tipo) {
        Categoria categoria = Categoria.buscarPorNome(nome);
        if (categoria != null) {
            nomeField.setText(categoria.getNome());
            descricaoField.setText(categoria.getDescricao());
            tipoCombo.setSelectedIndex(categoria.isReceita() ? 0 : 1);
            
            categoriaEmEdicao = nome;
            adicionarButton.setText("Salvar");
            editarButton.setEnabled(false);
        }
    }

    private void atualizarListasCategorias() {
        modelReceitas.clear();
        modelDespesas.clear();
        
        for (Categoria c : Categoria.listarReceitas()) {
            modelReceitas.addElement(c.getNome());
        }
        
        for (Categoria c : Categoria.listarDespesas()) {
            modelDespesas.addElement(c.getNome());
        }
    }

    private void limparCampos() {
        nomeField.setText("");
        descricaoField.setText("");
        tipoCombo.setSelectedIndex(0);
        categoriaEmEdicao = null;
        adicionarButton.setText("Adicionar");
        editarButton.setEnabled(true);
    }
}