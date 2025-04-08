package org.example.src.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RegistroGlobal {
    private static final List<Transacao> transacoes = new ArrayList<>();
    private static final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static void adicionar(Transacao transacao) {
        transacoes.add(transacao);
        System.out.println("Transação adicionada: " + transacao); // Log para depuração
    }

    public static List<Transacao> getTransacoes() {
        return new ArrayList<>(transacoes); // Retorna uma cópia da lista para evitar modificações externas
    }

    // Método para compatibilidade com código existente
    public static ArrayList<String> getRegistros() {
        ArrayList<String> registros = new ArrayList<>();
        for (Transacao t : transacoes) {
            registros.add(t.toString());
        }
        return registros;
    }

    public static double getTotalReceitas() {
        double total = 0;
        for (Transacao t : transacoes) {
            if (t.isReceita()) {
                total += t.getValor();
            }
        }
        return total;
    }

    public static double getTotalDespesas() {
        double total = 0;
        for (Transacao t : transacoes) {
            if (t.isDespesa()) {
                total += t.getValor();
            }
        }
        return total;
    }

    public static double getSaldoTotal() {
        return getTotalReceitas() - getTotalDespesas();
    }

    // Método para filtrar transações por período
    public static List filtrarPorPeriodo(String dataInicial, String dataFinal) {
        List filtradas = new ArrayList<>();

        try {
            // Converter strings para objetos Date
            Date dataIni = DATA_FORMAT.parse(dataInicial);
            Date dataFim = DATA_FORMAT.parse(dataFinal);

            // Se a data final for igual à data inicial, adiciona 1 dia à data final
            if (dataIni.equals(dataFim)) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dataFim);
                calendar.add(Calendar.DAY_OF_MONTH, 1); // Adiciona 1 dia
                dataFim = calendar.getTime();
            }

            // Ajustar a data final para incluir todo o dia
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataFim);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            dataFim = calendar.getTime();

            System.out.println("Filtrando transações de " + DATA_FORMAT.format(dataIni) + " até " + DATA_FORMAT.format(dataFim));
            System.out.println("Total de transações a verificar: " + transacoes.size());

            for (Transacao t : transacoes) {
                Date dataTransacao = t.getData();

                // Verificar se a data da transação está dentro do período
                if (!dataTransacao.before(dataIni) && !dataTransacao.after(dataFim)) {
                    filtradas.add(t);
                    System.out.println("Transação incluída no filtro: " + t + " (Data: " + DATA_FORMAT.format(t.getData()) + ")");
                } else {
                    System.out.println("Transação excluída do filtro: " + t + " (Data: " + DATA_FORMAT.format(t.getData()) + ")");
                }
            }

            System.out.println("Total de transações filtradas: " + filtradas.size());

        } catch (ParseException e) {
            System.err.println("Erro ao filtrar por período: " + e.getMessage());
            e.printStackTrace();
        }

        return filtradas;
    }
}