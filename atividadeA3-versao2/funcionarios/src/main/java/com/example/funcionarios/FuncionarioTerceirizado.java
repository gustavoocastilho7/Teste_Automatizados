package com.example.funcionarios;

public class FuncionarioTerceirizado extends Funcionario {
    private double despesasAdicionais;

    public double getDespesasAdicionais() {
        return despesasAdicionais;
    }

    public void setDespesasAdicionais(double despesasAdicionais) {
        if (despesasAdicionais < 0) {
            throw new IllegalArgumentException("As despesas nao podem ser negativas.");
        }
        if (despesasAdicionais > 1000.00) {
            throw new IllegalArgumentException("O valor das despesas adicionais nao pode ultrapassar R$ 1000.00.");
        }
        this.despesasAdicionais = despesasAdicionais;
    }

    @Override
    public double calcularPagamento() {
        double pagamentoBase = getHorasTrabalhadas() * getValorHora();
        double bonus = despesasAdicionais * 1.10;
        double pagamentoFinal = pagamentoBase + bonus;
        
        validarLimitesPagamento(pagamentoFinal);
        
        return pagamentoFinal;
    }
}
