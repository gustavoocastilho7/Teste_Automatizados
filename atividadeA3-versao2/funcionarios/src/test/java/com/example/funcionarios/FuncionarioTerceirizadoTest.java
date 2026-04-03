package com.example.funcionarios;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTerceirizadoTest {

    @Test
    public void testarModificarDespesaComValoresValidos() {
        FuncionarioTerceirizado terceirizado = new FuncionarioTerceirizado();
        terceirizado.setDespesasAdicionais(500.0);
        
        assertEquals(500.0, terceirizado.getDespesasAdicionais(), 0.01);
    }

    @Test
    public void testarModificarDespesaAcimaDoLimiteGeraErro() {
        FuncionarioTerceirizado terceirizado = new FuncionarioTerceirizado();
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            terceirizado.setDespesasAdicionais(1000.01);
        });
        assertEquals("O valor das despesas adicionais nao pode ultrapassar R$ 1000.00.", exception.getMessage());
    }

    @Test
    public void testarModificarDespesaAbaixoDeZeroGeraErro() {
        FuncionarioTerceirizado terceirizado = new FuncionarioTerceirizado();
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            terceirizado.setDespesasAdicionais(-10.0);
        });
        assertEquals("As despesas nao podem ser negativas.", exception.getMessage());
    }

    @Test
    public void testarCalcularPagamentoTerceirizadoValido() {
        FuncionarioTerceirizado terceirizado = new FuncionarioTerceirizado();
        terceirizado.setHorasTrabalhadas(100);
        terceirizado.setValorHora(50.0); 
        terceirizado.setDespesasAdicionais(1000.0); 
        
        assertEquals(6100.0, terceirizado.calcularPagamento(), 0.01);
    }

    @Test
    public void testarPagamentoTerceirizadoAcimaDoTetoGeraErro() {
        FuncionarioTerceirizado terceirizado = new FuncionarioTerceirizado();
        terceirizado.setHorasTrabalhadas(100);
        terceirizado.setValorHora(90.0); 
        terceirizado.setDespesasAdicionais(1000.0); 
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            terceirizado.calcularPagamento();
        });
        assertEquals("O salario nao pode ultrapassar o teto de R$ 10000.00.", exception.getMessage());
    }
}
