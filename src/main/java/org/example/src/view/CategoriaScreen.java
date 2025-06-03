package org.example.src.view;

import org.example.src.components.Styles;
import org.example.src.model.Categoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.List;

public class CategoriaScreen extends JFrame {
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

    public CategoriaScreen() {
        setTitle("Gerenciador de Categorias");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout(10, 10));

        // Painel de formulário
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Adicionar Categoria"));

        nomeField = new JTextField();
        tipoCombo = new JComboBox<>(new String[]{"Receita", "Despesa"});
        descricaoField = new JTextField();

        adicionarButton = new JButton("Adicionar");
        editarButton = new JButton("Editar");

        panelForm.add(new JLabel("Nome:"));
        panelForm.add(nomeField);
        panelForm.add(new JLabel("Tipo:"));
        panelForm.add(tipoCombo);
        panelForm.add(new JLabel("Descrição:"));
        panelForm.add(descricaoField);
        panelForm.add(adicionarButton);
        panelForm.add(editarButton);

        // Painel de listas
        modelReceitas = new DefaultListModel<>();
        modelDespesas = new DefaultListModel<>();
        listaReceitas = new JList<>(modelReceitas);
        listaDespesas = new JList<>(modelDespesas);

        JPanel panelListas = new JPanel(new GridLayout(1, 2, 10, 10));
        panelListas.setBorder(BorderFactory.createTitledBorder("Categorias Existentes"));

        panelListas.add(criarPainelLista("Receitas", listaReceitas));
        panelListas.add(criarPainelLista("Despesas", listaDespesas));

        // Botões inferiores
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        removerButton = new JButton("Remover");
        voltarButton = new JButton("Voltar");
        panelBotoes.add(removerButton);
        panelBotoes.add(voltarButton);

        add(panelForm, BorderLayout.NORTH);
        add(panelListas, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);

        Styles.centerOnScreen(this);
        setVisible(true);
    }

    private JPanel criarPainelLista(String titulo, JList<String> lista) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        panel.add(new JScrollPane(lista), BorderLayout.CENTER);
        return panel;
    }

    // === Getters ===
    public String getNome() {
        return nomeField.getText().trim();
    }

    public String getDescricao() {
        return descricaoField.getText().trim();
    }

    public Categoria.TipoCategoria getTipoSelecionado() {
        return tipoCombo.getSelectedIndex() == 0 ? Categoria.TipoCategoria.RECEITA : Categoria.TipoCategoria.DESPESA;
    }

    public void limparCampos() {
        nomeField.setText("");
        descricaoField.setText("");
        tipoCombo.setSelectedIndex(0);
        adicionarButton.setText("Adicionar");
        editarButton.setEnabled(true);
    }

    public void setCampos(String nome, String descricao, Categoria.TipoCategoria tipo) {
        nomeField.setText(nome);
        descricaoField.setText(descricao);
        tipoCombo.setSelectedIndex(tipo == Categoria.TipoCategoria.RECEITA ? 0 : 1);
        adicionarButton.setText("Salvar");
        editarButton.setEnabled(false);
    }

    public void setListaReceitas(List<Categoria> receitas) {
        modelReceitas.clear();
        for (Categoria c : receitas) {
            modelReceitas.addElement(c.getNome());
        }
    }

    public void setListaDespesas(List<Categoria> despesas) {
        modelDespesas.clear();
        for (Categoria c : despesas) {
            modelDespesas.addElement(c.getNome());
        }
    }

    public String getCategoriaReceitaSelecionada() {
        return listaReceitas.getSelectedValue();
    }

    public String getCategoriaDespesaSelecionada() {
        return listaDespesas.getSelectedValue();
    }

    // === Listeners ===
    public void setAdicionarListener(ActionListener l) {
        adicionarButton.addActionListener(l);
    }

    public void setEditarListener(ActionListener l) {
        editarButton.addActionListener(l);
    }

    public void setRemoverListener(ActionListener l) {
        removerButton.addActionListener(l);
    }

    public void setVoltarListener(ActionListener l) {
        voltarButton.addActionListener(l);
    }

    public void setDoubleClickReceita(MouseAdapter l) {
        listaReceitas.addMouseListener(l);
    }

    public void setDoubleClickDespesa(MouseAdapter l) {
        listaDespesas.addMouseListener(l);
    }

    public void showDialog(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public void showSuccess(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean confirmarRemocao(String nome) {
        int resposta = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja remover a categoria '" + nome + "'?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        return resposta == JOptionPane.YES_OPTION;
    }
}





//public class CategoriaScreen extends JFrame implements ActionListener {
//    private JTextField nomeField;
//    private JComboBox<String> tipoCombo;
//    private JTextField descricaoField;
//    private JList<String> listaReceitas;
//    private JList<String> listaDespesas;
//    private DefaultListModel<String> modelReceitas;
//    private DefaultListModel<String> modelDespesas;
//    private JButton adicionarButton;
//    private JButton editarButton;
//    private JButton removerButton;
//    private JButton voltarButton;
//    private String categoriaEmEdicao = null;
//    private JPanel panel;
//    private JTextField nomeField;
//    private JComboBox<String> tipoCombo;
//    private JTextField descricaoField;
//    private JList<String> listaReceitas;
//    private JList<String> listaDespesas;
//    private DefaultListModel<String> modelReceitas;
//    private DefaultListModel<String> modelDespesas;
//    private JButton adicionarButton;
//    private JButton editarButton;
//    private JButton removerButton;
//    private JButton voltarButton;
//
//    public void CategoriaPanelComponents(
//            JPanel panel,
//            JTextField nomeField,
//            JComboBox<String> tipoCombo,
//            JTextField descricaoField,
//            JList<String> listaReceitas,
//            JList<String> listaDespesas,
//            DefaultListModel<String> modelReceitas,
//            DefaultListModel<String> modelDespesas,
//            JButton adicionarButton,
//            JButton editarButton,
//            JButton removerButton,
//            JButton voltarButton) {
//        this.panel = panel;
//        this.nomeField = nomeField;
//        this.tipoCombo = tipoCombo;
//        this.descricaoField = descricaoField;
//        this.listaReceitas = listaReceitas;
//        this.listaDespesas = listaDespesas;
//        this.modelReceitas = modelReceitas;
//        this.modelDespesas = modelDespesas;
//        this.adicionarButton = adicionarButton;
//        this.editarButton = editarButton;
//        this.removerButton = removerButton;
//        this.voltarButton = voltarButton;
//    }
//
//    public CategoriaScreen() {
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setResizable(true);
//        setTitle("Gerenciador de Categorias");
//
//        CategoriaManager.CategoriaPanelComponents categoriaComponents =
//                CategoriaManager.createCategoriaPanel();
//        JPanel panelCategoria = new JPanel();
//        panelCategoria.setLayout(new BorderLayout(10, 10));
//
//        JPanel panelAdicionar = new JPanel(new GridLayout(4, 2, 10, 10));
//        panelAdicionar.setBorder(BorderFactory.createTitledBorder("Adicionar Categoria"));
//
//        JLabel labelNome = new JLabel("Nome:");
//        JTextField textNome = new JTextField(20);
//        Styles.setDefaultTextFieldSize(textNome);
//
//        JLabel labelTipo = new JLabel("Tipo:");
//        String[] tiposCategoria = {"Receita", "Despesa"};
//        JComboBox<String> comboTipo = new JComboBox<>(tiposCategoria);
//
//        JLabel labelDescricao = new JLabel("Descrição:");
//        JTextField textDescricao = new JTextField(30);
//        Styles.setDefaultTextFieldSize(textDescricao);
//
//        JButton buttonAdicionar = new JButton("Adicionar");
//        Styles.setDefaultButtonSize(buttonAdicionar);
//
//        JButton buttonEditar = new JButton("Editar");
//        Styles.setDefaultButtonSize(buttonEditar);
//
//        JButton buttonRemover = new JButton("Remover");
//        Styles.setDefaultButtonSize(buttonRemover);
//
//        JButton buttonVoltar = new JButton("Voltar");
//        Styles.setDefaultButtonSize(buttonVoltar);
//
//        panelAdicionar.add(labelNome);
//        panelAdicionar.add(textNome);
//        panelAdicionar.add(labelTipo);
//        panelAdicionar.add(comboTipo);
//        panelAdicionar.add(labelDescricao);
//        panelAdicionar.add(textDescricao);
//        panelAdicionar.add(buttonAdicionar);
//        panelAdicionar.add(buttonEditar);
//
//        JPanel panelListas = new JPanel(new GridLayout(1, 2, 10, 10));
//        panelListas.setBorder(BorderFactory.createTitledBorder("Categorias existentes"));
//
//        JPanel panelReceitas = new JPanel(new BorderLayout());
//        panelReceitas.setBorder(BorderFactory.createTitledBorder("Receitas"));
//        DefaultListModel<String> modelReceitas = new DefaultListModel<>();
//        for (Categoria c : Categoria.listarReceitas()) {
//            modelReceitas.addElement(c.getNome());
//        }
//        JList<String> listaReceitas = new JList<>(modelReceitas);
//        JScrollPane scrollReceitas = new JScrollPane(listaReceitas);
//        panelReceitas.add(scrollReceitas, BorderLayout.CENTER);
//
//        JPanel panelDespesas = new JPanel(new BorderLayout());
//        panelDespesas.setBorder(BorderFactory.createTitledBorder("Despesas"));
//        DefaultListModel<String> modelDespesas = new DefaultListModel<>();
//        for (Categoria c : Categoria.listarDespesas()) {
//            modelDespesas.addElement(c.getNome());
//        }
//        JList<String> listaDespesas = new JList<>(modelDespesas);
//        JScrollPane scrollDespesas = new JScrollPane(listaDespesas);
//        panelDespesas.add(scrollDespesas, BorderLayout.CENTER);
//
//        panelListas.add(panelReceitas);
//        panelListas.add(panelDespesas);
//
//        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        panelBotoes.add(buttonRemover);
//        panelBotoes.add(buttonVoltar);
//
//        panelCategoria.add(panelAdicionar, BorderLayout.NORTH);
//        panelCategoria.add(panelListas, BorderLayout.CENTER);
//        panelCategoria.add(panelBotoes, BorderLayout.SOUTH);
//
//        return new CategoriaManager.CategoriaPanelComponents(
//                panelCategoria,
//                textNome,
//                comboTipo,
//                textDescricao,
//                listaReceitas,
//                listaDespesas,
//                modelReceitas,
//                modelDespesas,
//                buttonAdicionar,
//                buttonEditar,
//                buttonRemover,
//                buttonVoltar
//        );
//
//
//
//
//
//        nomeField = nomeField;
//        tipoCombo = tipoCombo;
//        descricaoField = descricaoField;
//        listaReceitas = listaReceitas;
//        listaDespesas = listaDespesas;
//        modelReceitas = modelReceitas;
//        modelDespesas = modelDespesas;
//        adicionarButton = adicionarButton;
//        editarButton = editarButton;
//        removerButton = removerButton;
//        voltarButton = voltarButton;
//
//        adicionarButton.addActionListener(this);
//        editarButton.addActionListener(this);
//        removerButton.addActionListener(this);
//        voltarButton.addActionListener(this);
//
//        listaReceitas.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 2) {
//                    carregarCategoriaSelecionada(listaReceitas.getSelectedValue(), Categoria.TipoCategoria.RECEITA);
//                }
//            }
//        });
//
//        listaDespesas.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 2) {
//                    carregarCategoriaSelecionada(listaDespesas.getSelectedValue(), Categoria.TipoCategoria.DESPESA);
//                }
//            }
//        });
//
//        add(panel);
//        setSize(600, 500);
//        Styles.centerOnScreen(this);
//        setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == adicionarButton) {
//            adicionarCategoria();
//        } else if (e.getSource() == editarButton) {
//            editarCategoria();
//        } else if (e.getSource() == removerButton) {
//            removerCategoriaSelecionada();
//        } else if (e.getSource() == voltarButton) {
//            dispose();
//        }
//    }
//
//    private void adicionarCategoria() {
//        String nome = nomeField.getText().trim();
//        String descricao = descricaoField.getText().trim();
//        Categoria.TipoCategoria tipo = (tipoCombo.getSelectedIndex() == 0) ?
//                Categoria.TipoCategoria.RECEITA : Categoria.TipoCategoria.DESPESA;
//
//        if (nome.isEmpty()) {
//            JOptionPane.showMessageDialog(this,
//                    "O nome da categoria é obrigatório.",
//                    "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//
//        if (categoriaEmEdicao != null) {
//            Categoria.removerPorNome(categoriaEmEdicao);
//            atualizarListasCategorias();
//            categoriaEmEdicao = null;
//            adicionarButton.setText("Adicionar");
//            editarButton.setEnabled(true);
//        }
//
//        if (Categoria.existeCategoria(nome)) {
//            JOptionPane.showMessageDialog(this,
//                    "Já existe uma categoria com este nome.",
//                    "Categoria duplicada", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//
//        Categoria novaCategoria = new Categoria(nome, tipo, descricao);
//        Categoria.adicionar(novaCategoria);
//
//        atualizarListasCategorias();
//
//        limparCampos();
//
//        JOptionPane.showMessageDialog(this,
//                "Categoria adicionada com sucesso!",
//                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
//    }
//
//    private void editarCategoria() {
//        String nomeSelecionado = null;
//        Categoria.TipoCategoria tipoSelecionado = null;
//
//        if (listaReceitas.getSelectedValue() != null) {
//            nomeSelecionado = listaReceitas.getSelectedValue();
//            tipoSelecionado = Categoria.TipoCategoria.RECEITA;
//        } else if (listaDespesas.getSelectedValue() != null) {
//            nomeSelecionado = listaDespesas.getSelectedValue();
//            tipoSelecionado = Categoria.TipoCategoria.DESPESA;
//        }
//
//        if (nomeSelecionado == null) {
//            JOptionPane.showMessageDialog(this,
//                    "Selecione uma categoria para editar.",
//                    "Seleção requerida", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//
//        carregarCategoriaSelecionada(nomeSelecionado, tipoSelecionado);
//    }
//
//    private void removerCategoriaSelecionada() {
//        String nomeSelecionado = null;
//
//        if (listaReceitas.getSelectedValue() != null) {
//            nomeSelecionado = listaReceitas.getSelectedValue();
//        } else if (listaDespesas.getSelectedValue() != null) {
//            nomeSelecionado = listaDespesas.getSelectedValue();
//        }
//
//        if (nomeSelecionado == null) {
//            JOptionPane.showMessageDialog(this,
//                    "Selecione uma categoria para remover.",
//                    "Seleção requerida", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//
//        int resposta = JOptionPane.showConfirmDialog(this,
//                "Tem certeza que deseja remover a categoria '" + nomeSelecionado + "'?",
//                "Confirmar exclusão",
//                JOptionPane.YES_NO_OPTION,
//                JOptionPane.QUESTION_MESSAGE);
//
//        if (resposta == JOptionPane.YES_OPTION) {
//            if (Categoria.removerPorNome(nomeSelecionado)) {
//                atualizarListasCategorias();
//
//                JOptionPane.showMessageDialog(this,
//                        "Categoria removida com sucesso!",
//                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
//            } else {
//                JOptionPane.showMessageDialog(this,
//                        "Erro ao remover categoria.",
//                        "Erro", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
//
//    private void carregarCategoriaSelecionada(String nome, Categoria.TipoCategoria tipo) {
//        Categoria categoria = Categoria.buscarPorNome(nome);
//        if (categoria != null) {
//            nomeField.setText(categoria.getNome());
//            descricaoField.setText(categoria.getDescricao());
//            tipoCombo.setSelectedIndex(categoria.isReceita() ? 0 : 1);
//
//            categoriaEmEdicao = nome;
//            adicionarButton.setText("Salvar");
//            editarButton.setEnabled(false);
//        }
//    }
//
//    private void atualizarListasCategorias() {
//        modelReceitas.clear();
//        modelDespesas.clear();
//
//        for (Categoria c : Categoria.listarReceitas()) {
//            modelReceitas.addElement(c.getNome());
//        }
//
//        for (Categoria c : Categoria.listarDespesas()) {
//            modelDespesas.addElement(c.getNome());
//        }
//    }
//
//    private void limparCampos() {
//        nomeField.setText("");
//        descricaoField.setText("");
//        tipoCombo.setSelectedIndex(0);
//        categoriaEmEdicao = null;
//        adicionarButton.setText("Adicionar");
//        editarButton.setEnabled(true);
//    }
//}