package com.example.funcionarios;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTest {

    @Test
    public void testarModificarHorasComValoresValidos() {
        Funcionario funcionario = new Funcionario();
        funcionario.setHorasTrabalhadas(100);
        
        assertEquals(100, funcionario.getHorasTrabalhadas());
    }

    @Test
    public void testarModificarHorasAbaixoLimiteInferiorGeraErro() {
        Funcionario funcionario = new Funcionario();
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setHorasTrabalhadas(19);
        });
        assertEquals("Horas mensais devem estar entre 20 e 160.", exception.getMessage());
    }

    @Test
    public void testarModificarHorasAcimaLimiteSuperiorGeraErro() {
        Funcionario funcionario = new Funcionario();
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setHorasTrabalhadas(161);
        });
        assertEquals("Horas mensais devem estar entre 20 e 160.", exception.getMessage());
    }

    @Test
    public void testarModificarValorPorHoraComValoresValidos() {
        Funcionario funcionario = new Funcionario();
        funcionario.setValorHora(50.0);
        
        assertEquals(50.0, funcionario.getValorHora(), 0.01);
    }

    @Test
    public void testarModificarValorPorHoraAbaixoLimiteInferiorGeraErro() {
        Funcionario funcionario = new Funcionario();
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setValorHora(15.0); 
        });
        assertEquals("O valor da hora deve ser entre R$ 15.18 e R$ 151.80.", exception.getMessage());
    }

    @Test
    public void testarModificarValorPorHoraAcimaLimiteSuperiorGeraErro() {
        Funcionario funcionario = new Funcionario();
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setValorHora(152.0); 
        });
        assertEquals("O valor da hora deve ser entre R$ 15.18 e R$ 151.80.", exception.getMessage());
    }

    @Test
    public void testarCalcularPagamentoValido() {
        Funcionario funcionario = new Funcionario();
        funcionario.setHorasTrabalhadas(100);
        funcionario.setValorHora(50.0);
        
        assertEquals(5000.0, funcionario.calcularPagamento(), 0.01);
    }

    @Test
    public void testarPagamentoAbaixoDoSalarioMinimoGeraErro() {
        Funcionario funcionario = new Funcionario();
        funcionario.setHorasTrabalhadas(20);
        funcionario.setValorHora(20.0); 
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            funcionario.calcularPagamento();
        });
        assertEquals("O pagamento nao pode ser menor que o salario minimo (R$ 1518.00).", exception.getMessage());
    }

    @Test
    public void testarPagamentoAcimaDoTetoGeraErro() {
        Funcionario funcionario = new Funcionario();
        funcionario.setHorasTrabalhadas(160);
        funcionario.setValorHora(100.0); 
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            funcionario.calcularPagamento();
        });
        assertEquals("O salario nao pode ultrapassar o teto de R$ 10000.00.", exception.getMessage());
    }
}
