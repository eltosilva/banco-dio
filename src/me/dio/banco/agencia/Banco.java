package me.dio.banco.agencia;

import me.dio.banco.cliente.Cliente;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Banco {
    private static final Set<Agencia> agencias = new HashSet<>();
    private static final Set<Cliente> clientes = new HashSet<>();
    private static LocalDate data = LocalDate.of(2022, 1, 1);

    public static void addAgencia(Agencia agencia){
        agencias.add(agencia);
    }

    public static void addCliente(Cliente cliente){
        clientes.add(cliente);
    }

    public static LocalDate getData(){
        return data;
    }

    public static void passagemDoTempoEmDias(int quantDias){
        for(int i = 0; i < quantDias; i++){
            data = data.plusDays(1);
            for (Agencia agencia: agencias)
                agencia.atualizarPoupancas();
        }
    }

    public static Set<Cliente> getClientes(){
        return new HashSet<>(clientes);
    }

    public static Set<Agencia> getAgencias(){
        return new HashSet<>(agencias);
    }
}