package com.mobiauto.utils;

import com.mobiauto.domain.exception.RegrasDeNegocioException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FormataData {

    private static List<String> datas = new ArrayList<>();
    private static boolean add = false;
    private static int contador = 0;

    public static final String formateMinhaData(Date data) {
        if (data != null) {
            // Criar um formato desejado
            SimpleDateFormat meuFormato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            // Formatar a data em uma string
            String dataFormatada = meuFormato.format(data);
            return dataFormatada;
        } else {
            System.out.println("Data ta nula na classe FormataData");
            throw new RegrasDeNegocioException("Erro interno do sistema!");
        }
    }


    public static final String verificarMes(int mes) {

        if (mes == 1) {
            return "Jan";
        } else if (mes == 2) {
            return "Fev";
        } else if (mes == 3) {
            return "Mar";
        } else if (mes == 4) {
            return "Abril";
        } else if (mes == 5) {
            return "Maio";
        } else if (mes == 6) {
            return "Jun";
        } else if (mes == 7) {
            return "Jul";
        } else if (mes == 8) {
            return "Ago";
        } else if (mes == 9) {
            return "Set";
        } else if (mes == 10) {
            return "Out";
        } else if (mes == 11) {
            return "Nov";
        } else if (mes == 12) {
            return "Dez";
        } else {
            return "";
        }

    }

    public static final String verificarMesEAno(int mes, int ano) {

        add = false;

        String mesSelecionado = "";

        if (mes == 1) {
            mesSelecionado = "Jan";
        } else if (mes == 2) {
            mesSelecionado = "Fev";
        } else if (mes == 3) {
            mesSelecionado = "Mar";
        } else if (mes == 4) {
            mesSelecionado = "Abril";
        } else if (mes == 5) {
            mesSelecionado = "Maio";
        } else if (mes == 6) {
            mesSelecionado = "Jun";
        } else if (mes == 7) {
            mesSelecionado = "Jul";
        } else if (mes == 8) {
            mesSelecionado = "Ago";
        } else if (mes == 9) {
            mesSelecionado = "Set";
        } else if (mes == 10) {
            mesSelecionado = "Out";
        } else if (mes == 11) {
            mesSelecionado = "Nov";
        } else if (mes == 12) {
            mesSelecionado = "Dez";
        }

        String data = mesSelecionado + "/" + ano;

        datas.forEach((dt) -> {
            if (dt.equals(data)) { // caso ja tenha add
                add = true;
            }
        });

        if (add == false) { // não está na lista, pode add
            datas.add(data);
        }

        return data;

    }

    public static void limparDatas() {
        datas.clear();
    }


}
