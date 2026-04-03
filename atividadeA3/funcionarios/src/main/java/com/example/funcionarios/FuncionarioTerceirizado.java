package com.example.funcionarios;


public class FuncionarioTerceirizado extends Funcionario {
    private double despesasAdicionais;

    public double getDespesasAdicionais() { return despesasAdicionais; }
    public void setDespesasAdicionais(double despesasAdicionais) { }

    @Override
    public double calcularPagamento() { return 0.0; }
}
