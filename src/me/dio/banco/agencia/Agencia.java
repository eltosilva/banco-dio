package me.dio.banco.agencia;

import me.dio.banco.cliente.Cliente;
import me.dio.banco.conta.Conta;
import me.dio.banco.conta.ContaCorrente;
import me.dio.banco.conta.ContaPoupanca;

import java.util.HashSet;
import java.util.Set;

public class Agencia {
    private static int contadorDeAgenciasAbertas = 0;
    private int contadorDeContasAbertas;
    private final int id;
    private final String endereco;

    private Set<Conta> contas;

    public Agencia(String endereco){
        this.id = ++Agencia.contadorDeAgenciasAbertas;
        this.endereco = endereco;
        this.contadorDeContasAbertas = 0;
        this.contas = new HashSet<>();
    }

    public Conta abrirContaCorrente(Cliente proprietarioDaConta){
        return adicionarConta(new ContaCorrente(this, ++contadorDeContasAbertas, proprietarioDaConta));
    }

    public Conta abrirContaPoupanca(Cliente proprietarioDaConta){
        return adicionarConta(new ContaPoupanca(this, ++this.contadorDeContasAbertas, proprietarioDaConta));
    }

    private Conta adicionarConta(Conta conta){
        contas.add(conta);
        return conta;
    }

    public Set<Conta> consultarContasPorCliente(Cliente cliente){
        Set<Conta> set = new HashSet<>();
        for (Conta conta : contas) {
            if (conta.temOMesmoDono(cliente)) {
                set.add(conta);
            }
        }
        return set;
    }

    public int getId() {
        return id;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return "Agencia{" +
                "id=" + id +
                ", endereco='" + endereco + '\'' +
                '}';
    }

    public void atualizarPoupancas() {
        for(Conta conta : contas)
            if(conta.getClass().equals(ContaPoupanca.class)){
                ContaPoupanca cp = (ContaPoupanca) conta;
                cp.creditarJuros();
            }
    }
}
