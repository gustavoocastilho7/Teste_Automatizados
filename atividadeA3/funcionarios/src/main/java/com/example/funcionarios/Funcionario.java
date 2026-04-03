package com.example.funcionarios;

public class Funcionario {
    private String nome;
    private int horasTrabalhadas;
    private double valorHora;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getHorasTrabalhadas() { return horasTrabalhadas; }
    public void setHorasTrabalhadas(int horasTrabalhadas) { }

    public double getValorHora() { return valorHora; }
    public void setValorHora(double valorHora) { }

    public double calcularPagamento() { return 0.0; }
}
