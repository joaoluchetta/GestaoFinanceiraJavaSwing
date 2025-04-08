package org.example.src.models;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    public enum TipoCategoria {
        RECEITA,
        DESPESA
    }

    private String nome;
    private TipoCategoria tipo;
    private String descricao;

    // Armazenamento em memória para categorias
    private static final List<Categoria> categorias = new ArrayList<>();

    // Inicializar com algumas categorias padrão
    static {
        // Categorias de Receita
        categorias.add(new Categoria("Salário", TipoCategoria.RECEITA, "Salário mensal"));
        categorias.add(new Categoria("Freelance", TipoCategoria.RECEITA, "Trabalhos extras"));
        categorias.add(new Categoria("Investimentos", TipoCategoria.RECEITA, "Rendimentos de investimentos"));

        // Categorias de Despesa
        categorias.add(new Categoria("Alimentação", TipoCategoria.DESPESA, "Gastos com comida"));
        categorias.add(new Categoria("Moradia", TipoCategoria.DESPESA, "Aluguel, contas, etc."));
        categorias.add(new Categoria("Transporte", TipoCategoria.DESPESA, "Combustível, transporte público"));
    }

    public Categoria(String nome, TipoCategoria tipo, String descricao) {
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    // Getters e Setters
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

    // Métodos estáticos para gerenciar categorias

    public static List<Categoria> listarTodas() {
        return new ArrayList<>(categorias);
    }

    public static List<Categoria> listarReceitas() {
        List<Categoria> receitas = new ArrayList<>();
        for (Categoria categoria : categorias) {
            if (categoria.isReceita()) {
                receitas.add(categoria);
            }
        }
        return receitas;
    }

    public static List<Categoria> listarDespesas() {
        List<Categoria> despesas = new ArrayList<>();
        for (Categoria categoria : categorias) {
            if (categoria.isDespesa()) {
                despesas.add(categoria);
            }
        }
        return despesas;
    }

    public static void adicionar(Categoria categoria) {
        categorias.add(categoria);
    }

    public static boolean remover(Categoria categoria) {
        return categorias.remove(categoria);
    }

    public static boolean removerPorNome(String nome) {
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getNome().equals(nome)) {
                categorias.remove(i);
                return true;
            }
        }
        return false;
    }

    public static Categoria buscarPorNome(String nome) {
        for (Categoria categoria : categorias) {
            if (categoria.getNome().equals(nome)) {
                return categoria;
            }
        }
        return null;
    }

    public static boolean existeCategoria(String nome) {
        return buscarPorNome(nome) != null;
    }

    public static String[] getNomesCategorias(TipoCategoria tipo) {
        List<String> nomes = new ArrayList<>();
        for (Categoria categoria : categorias) {
            if (categoria.getTipo() == tipo) {
                nomes.add(categoria.getNome());
            }
        }
        return nomes.toArray(new String[0]);
    }
}