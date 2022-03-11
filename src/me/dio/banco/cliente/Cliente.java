package me.dio.banco.cliente;

import java.util.Objects;

public class Cliente {
    private String nome;
    private String endereco;
    private String cpf;

    public Cliente(String nome, String endereco, String cpf){
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return nome.equals(cliente.nome) && endereco.equals(cliente.endereco) && cpf.equals(cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, endereco, cpf);
    }

    public boolean cpfIgual(String cpf) {
        return this.cpf.equals(cpf);
    }
}
