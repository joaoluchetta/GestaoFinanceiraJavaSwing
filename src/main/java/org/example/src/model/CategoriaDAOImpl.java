package org.example.src.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements  CategoriaDAO{

    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();


//    private static final List<Categoria> categorias = new ArrayList<>();
//    static {
//        // Categorias de Receita
//        categorias.add(new Categoria("Salário", Categoria.TipoCategoria.RECEITA, "Salário mensal"));
//        categorias.add(new Categoria("Freelance", Categoria.TipoCategoria.RECEITA, "Trabalhos extras"));
//        categorias.add(new Categoria("Investimentos", Categoria.TipoCategoria.RECEITA, "Rendimentos de investimentos"));
//
//        // Categorias de Despesa
//        categorias.add(new Categoria("Alimentação", Categoria.TipoCategoria.DESPESA, "Gastos com comida"));
//        categorias.add(new Categoria("Moradia", Categoria.TipoCategoria.DESPESA, "Aluguel, contas, etc."));
//        categorias.add(new Categoria("Transporte", Categoria.TipoCategoria.DESPESA, "Combustível, transporte público"));
//    }


    @Override
    public List<Categoria> listarReceitas() {
        try(Session session = factory.openSession()){
            return session.createQuery("FROM Categoria WHERE tipo = :tipo", Categoria.class)
                    .setParameter("tipo", Categoria.TipoCategoria.RECEITA)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Categoria> listarDespesas() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Categoria WHERE tipo = :tipo", Categoria.class)
                    .setParameter("tipo", Categoria.TipoCategoria.DESPESA)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void adicionar(Categoria categoria) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(categoria);
            tx.commit();
        }
    }

    @Override
    public boolean removerPorNome(String nome) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Categoria categoria = buscarPorNome(nome);
            if (categoria != null) {
                session.remove(categoria);
                tx.commit();
                return true;
            }
            return false;
        }
    }

    @Override
    public Categoria buscarPorNome(String nome) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Categoria WHERE nome = :nome", Categoria.class)
                    .setParameter("nome", nome)
                    .uniqueResult();
        }
    }

    @Override
    public boolean existeCategoria(String nome) {
        return buscarPorNome(nome) != null;
    }

    @Override
    public String[] getNomesCategorias(Categoria.TipoCategoria tipo) {
        try (Session session = factory.openSession()) {
            List<String> nomes = session.createQuery(
                            "SELECT nome FROM Categoria WHERE tipo = :tipo", String.class)
                    .setParameter("tipo", tipo)
                    .list();
            return nomes.toArray(new String[0]);
        }
    }
}
