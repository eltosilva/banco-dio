package me.dio.banco.conta;

import me.dio.banco.agencia.Agencia;
import me.dio.banco.cliente.Cliente;

public class ContaCorrente extends Conta{
    public ContaCorrente(Agencia agencia, int numeroDaConta, Cliente proprietarioDaConta) {
        super(agencia, numeroDaConta, proprietarioDaConta);
    }

    @Override
    public boolean depositar(double valor){
        return super.creditar(valor, DescricaoMovimentacao.DEPOSITO);
    }

    @Override
    public boolean sacar(double valor){
        return super.debitar(valor, DescricaoMovimentacao.SAQUE);
    }

    @Override
    public boolean transferir(double valor, Conta contaDestino){
        if(debitar(valor, DescricaoMovimentacao.TRANSFFERENCIA))
            return contaDestino.creditar(valor, DescricaoMovimentacao.TRANSFFERENCIA);

        return false;
    }

    @Override
    public String toString() {
        return "Agência: " + this.getAgencia().getId() + " Conta Corrente: " + this.getNumero();
    }
}
