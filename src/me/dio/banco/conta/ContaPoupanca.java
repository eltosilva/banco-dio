package me.dio.banco.conta;

import me.dio.banco.agencia.Agencia;
import me.dio.banco.agencia.Banco;
import me.dio.banco.cliente.Cliente;

import java.time.LocalDate;

public class ContaPoupanca extends Conta{

    private final int QUANT_DIAS_FEVEREIRO = 28;

    private final double[] saldosPorDiaBase;

    public ContaPoupanca(Agencia agencia, int numero, Cliente proprietarioDaConta) {
        super(agencia, numero, proprietarioDaConta);
        saldosPorDiaBase = new double[QUANT_DIAS_FEVEREIRO];
    }

    @Override
    public boolean transferir(double valor, Conta contaDestino) {
        if(debitar(valor, DescricaoMovimentacao.TRANSFFERENCIA)){
            contaDestino.creditar(valor, DescricaoMovimentacao.TRANSFFERENCIA);
        }

        return false;
    }

    @Override
    public boolean sacar(double valor) {
        return debitar(valor, DescricaoMovimentacao.SAQUE);
    }

    @Override
    public boolean depositar(double valor) {
        return creditar(valor, DescricaoMovimentacao.DEPOSITO);
    }

    @Override
    protected boolean creditar(double valor, DescricaoMovimentacao descricaoMovimentacao){
        boolean status = super.creditar(valor, descricaoMovimentacao);

        if(status)
            saldosPorDiaBase[getIndexDiaBase(Banco.getData().getDayOfMonth())] += valor;

        return status;
    }

    @Override
    protected boolean debitar(double valor, DescricaoMovimentacao descricaoMovimentacao) {
        if(getSaldo() < valor)
            return false;

        LocalDate umMesAntes = Banco.getData().minusMonths(1).minusDays(1);
        LocalDate data = Banco.getData().minusDays(1);
        int index;

        while (data.isAfter(umMesAntes) && valor > 0){
            index = getIndexDiaBase(data.getDayOfMonth());

            if(saldosPorDiaBase[index] > 0){
                if(saldosPorDiaBase[index] >= valor){
                    super.debitar(valor, descricaoMovimentacao);
                    saldosPorDiaBase[index] -= valor;
                    valor = 0;
                } else {
                    super.debitar(saldosPorDiaBase[index], descricaoMovimentacao);
                    valor -= saldosPorDiaBase[index];
                    saldosPorDiaBase[index] = 0;
                }
            }
            data = data.minusDays(1);
        }

        return true;
    }

    private int getIndexDiaBase(int diaDoMes){
        return diaDoMes >= QUANT_DIAS_FEVEREIRO ? QUANT_DIAS_FEVEREIRO - 1 : diaDoMes - 1;
    }

    public void creditarJuros() {
        int indexDiaBase = getIndexDiaBase(Banco.getData().getDayOfMonth());

        if (saldosPorDiaBase[indexDiaBase] > 0){
            double valor = saldosPorDiaBase[indexDiaBase] * 0.005;
            creditar(valor, DescricaoMovimentacao.JUROS);
        }
    }

    @Override
    public String toString() {
        return "Agência: " + this.getAgencia().getId() + " Conta Poupanca: " + this.getNumero();
    }

}
