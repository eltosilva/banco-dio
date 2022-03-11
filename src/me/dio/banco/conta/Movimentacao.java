package me.dio.banco.conta;

import me.dio.banco.agencia.Banco;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Movimentacao {
    private final double valor;
    private final DescricaoMovimentacao descricao;
    private final TipoMovimentacao tipo;
    private final LocalDate dataHora;

    public Movimentacao(double valor, DescricaoMovimentacao descricaoMovimentacaoovimentacao, TipoMovimentacao tipoMovimentacao) {
        this.valor = valor;
        this.descricao = descricaoMovimentacaoovimentacao;
        this.tipo = tipoMovimentacao;
        this.dataHora = Banco.getData();
    }

    public boolean ehMesmoMes(Month mes){
        return dataHora.getMonth().equals(mes);
    }

    @Override
    public String toString() {
        return valor + "\t" + descricao.toString() + "\t" +
            tipo.toString() + "\t" + dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yy")) + "\n";
    }
}
