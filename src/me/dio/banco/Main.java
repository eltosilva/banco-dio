package me.dio.banco;

import me.dio.banco.agencia.Agencia;
import me.dio.banco.agencia.Banco;
import me.dio.banco.cliente.Cliente;
import me.dio.banco.conta.Conta;

import java.time.Month;

public class Main {

    public static void main(String[] args) {
        Agencia agencia = new Agencia("Rua Dom Pedro II");
        Banco.addAgencia(agencia);

        Cliente cliente = new Cliente("João", "Rua 7 de Setembro", "12345");
        Banco.addCliente(cliente);

        Conta contaJoao = agencia.abrirContaCorrente(cliente);
        contaJoao.depositar(1000);

        cliente = new Cliente("Maria", "Rua 22 de Maio", "54321");
        Conta contaMaria = agencia.abrirContaPoupanca(cliente);

        contaJoao.transferir(300, contaMaria);
        contaJoao.sacar(100);


        System.out.println(Banco.getAgencias());
        System.out.println(Banco.getClientes());
        System.out.println();

        Banco.passagemDoTempoEmDias(10);
        contaMaria.depositar(100);

        Banco.passagemDoTempoEmDias(25);
        contaMaria.sacar(320);

        System.out.println(contaJoao.getExtratoDoMes(Month.JANUARY));
        System.out.println(contaJoao.getSaldo());
        System.out.println();
        System.out.println(contaMaria.getExtratoDoMes(Month.JANUARY));
        System.out.println(contaMaria.getExtratoDoMes(Month.FEBRUARY));
        System.out.println(contaMaria.getSaldo());
    }
}
