package com.example.funcionarios;

public class Funcionario {
    private String nome;
    private int horasTrabalhadas;
    private double valorHora;
    
    protected static final double SALARIO_MINIMO = 1518.00;
    protected static final double TETO_SALARIAL = 10000.00;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(int horasTrabalhadas) {
        if (horasTrabalhadas < 20 || horasTrabalhadas > 160) {
            throw new IllegalArgumentException("Horas mensais devem estar entre 20 e 160.");
        }
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        double limiteInferior = SALARIO_MINIMO * 0.01; 
        double limiteSuperior = SALARIO_MINIMO * 0.10; 
        
        if (valorHora < limiteInferior || valorHora > limiteSuperior) {
            throw new IllegalArgumentException("O valor da hora deve ser entre R$ 15.18 e R$ 151.80.");
        }
        this.valorHora = valorHora;
    }

    public double calcularPagamento() {
        double pagamento = horasTrabalhadas * valorHora;
        validarLimitesPagamento(pagamento);
        return pagamento;
    }

    protected void validarLimitesPagamento(double pagamentoFinal) {
        if (pagamentoFinal < SALARIO_MINIMO) {
            throw new IllegalArgumentException("O pagamento nao pode ser menor que o salario minimo (R$ 1518.00).");
        }
        if (pagamentoFinal > TETO_SALARIAL) {
            throw new IllegalArgumentException("O salario nao pode ultrapassar o teto de R$ 10000.00.");
        }
    }
}
