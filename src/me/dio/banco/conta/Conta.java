package me.dio.banco.conta;

import me.dio.banco.agencia.Agencia;
import me.dio.banco.cliente.Cliente;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Conta{
    private final Agencia agencia;
    private final int numero;
    private final Cliente cliente;
    private double saldo;
    private List<Movimentacao> movimentacoes;

    public Conta(Agencia agencia, int numero, Cliente proprietarioDaConta) {
        this.agencia = agencia;
        this.numero = numero;
        this.cliente = proprietarioDaConta;
        this.saldo = 0;
        this.movimentacoes = new ArrayList<>();
    }

    public abstract boolean depositar(double valor);

    public abstract boolean sacar(double valor);

    public abstract boolean transferir(double valor, Conta contaDestino);

    protected boolean creditar(double valor, DescricaoMovimentacao descricaoMovimentacao){
        if(valor < 0)
            return false;
        saldo += valor;
        movimentacoes.add(new Movimentacao(valor, descricaoMovimentacao, TipoMovimentacao.CREDITO));
        return true;
    }

    protected boolean debitar(double valor, DescricaoMovimentacao descricaoMovimentacao){
        if(valor < 0)
            return false;
        if(saldo < valor)
            return false;
        saldo -= valor;
        movimentacoes.add(new Movimentacao(valor, descricaoMovimentacao, TipoMovimentacao.DEBITO));
        return true;
    }

    public boolean temOMesmoDono(Cliente cliente){
        return this.cliente.equals(cliente);
    }

    public Agencia getAgencia(){
        return  this.agencia;
    }

    public int getNumero(){
        return this.numero;
    }

    public double getSaldo(){
        return  this.saldo;
    }

    public List<Movimentacao> getExtratoDoMes(Month mes){
        return movimentacoes.stream().filter(movimentacao -> movimentacao.ehMesmoMes(mes)).collect(Collectors.toList());
    }
}
