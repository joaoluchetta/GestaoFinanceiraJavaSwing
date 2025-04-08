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
    }

    public static List<Transacao> getTransacoes() {
        return new ArrayList<>(transacoes);
    }
    
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
    
    public static List filtrarPorPeriodo(String dataInicial, String dataFinal) {
        List filtradas = new ArrayList<>();

        try {
            Date dataIni = DATA_FORMAT.parse(dataInicial);
            Date dataFim = DATA_FORMAT.parse(dataFinal);

            // Se a data final for igual à data inicial, adiciona 1 dia à data final para nao quebrar o codigo
            if (dataIni.equals(dataFim)) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dataFim);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                dataFim = calendar.getTime();
            }
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataFim);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            dataFim = calendar.getTime();

            for (Transacao t : transacoes) {
                Date dataTransacao = t.getData();
                
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