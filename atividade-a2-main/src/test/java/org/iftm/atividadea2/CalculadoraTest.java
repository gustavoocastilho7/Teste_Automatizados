package org.iftm.atividadea2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculadoraTest {

    // 1
    @Test
    public void testarConstrutorVazioInicializaMemoriaZerada() {
        // arrange
        int memoriaEsperada = 0;
        // act
        Calculadora calculadora = new Calculadora();
        int memoriaObtida = calculadora.getMemoria();
        // asserts
        assertEquals(memoriaEsperada, memoriaObtida);
    }

    // 2.1
    @Test
    public void testarConstrutorComParametroPositivo() {
        // arrange

        int memoriaEsperada = 3;
        // act
        Calculadora calculadora = new Calculadora(3);
        int memoriaObtida = calculadora.getMemoria();
        // asserts
        assertEquals(memoriaEsperada, memoriaObtida);
    }

    // 2.2
    @Test
    public void testarConstrutorComParametroNegativo() {
        // arrange

        int memoriaEsperada = -3;
        // act
        Calculadora calculadora = new Calculadora(-3);
        int memoriaObtida = calculadora.getMemoria();
        // asserts
        assertEquals(memoriaEsperada, memoriaObtida);
    }

    // 3.1
    @Test
    public void testarSomaNumeroInteiroPositivo() {

        // arrange
        int memoriaInicial = 3;
        int valorASerSomado = 3;
        int memoriaEsperada = 6;

        // act
        Calculadora calculadora = new Calculadora(memoriaInicial);
        calculadora.somar(valorASerSomado);
        int memoriaObtida = calculadora.getMemoria();

        // asserts
        assertEquals(memoriaEsperada, memoriaObtida);
    }

    // 3.2
    @Test
    public void testarSomaNumeroInteiroNegativo() {

        // arrange
        int memoriaInicial = 3;
        int valorASerSomado = -3;
        int memoriaEsperada = 0;

        // act
        Calculadora calculadora = new Calculadora(memoriaInicial);
        calculadora.somar(valorASerSomado);
        int memoriaObtida = calculadora.getMemoria();

        // asserts
        assertEquals(memoriaEsperada, memoriaObtida);
    }

    // 4.1
    @Test
    public void testarSubtrairNumeroInteiroPositivo() {

        // arrange
        int memoriaInicial = 3;
        int valorASerSubtraido = 3;
        int memoriaEsperada = 0;

        // act
        Calculadora calculadora = new Calculadora(memoriaInicial);
        calculadora.subtrair(valorASerSubtraido);
        int memoriaObtida = calculadora.getMemoria();

        // asserts
        assertEquals(memoriaEsperada, memoriaObtida);
    }

    // 4.2
    @Test
    public void testarSubtrairNumeroInteiroNegativo() {

        // arrange
        int memoriaInicial = 3;
        int valorASerSubtraido = -3;
        int memoriaEsperada = 6;

        // act
        Calculadora calculadora = new Calculadora(memoriaInicial);
        calculadora.subtrair(valorASerSubtraido);
        int memoriaObtida = calculadora.getMemoria();

        // asserts
        assertEquals(memoriaEsperada, memoriaObtida);
    }

    // 5.1
    @Test
    public void testarMultiplicarNumeroInteiroPositivo() {

        // arrange
        int memoriaInicial = 3;
        int valorASerMultiplicado = 3;
        int memoriaEsperada = 9;

        // act
        Calculadora calculadora = new Calculadora(memoriaInicial);
        calculadora.multiplicar(valorASerMultiplicado);
        int memoriaObtida = calculadora.getMemoria();

        // asserts
        assertEquals(memoriaEsperada, memoriaObtida);
    }

    // 5.2
    @Test
    public void testarMultiplicarNumeroInteiroNegativo() {

        // arrange
        int memoriaInicial = 3;
        int valorASerMultiplicado = -3;
        int memoriaEsperada = -9;

        // act
        Calculadora calculadora = new Calculadora(memoriaInicial);
        calculadora.multiplicar(valorASerMultiplicado);
        int memoriaObtida = calculadora.getMemoria();

        // asserts
        assertEquals(memoriaEsperada, memoriaObtida);
    }

    // 6.1
    @Test
    public void testarDividirNumeroInteiroPositivo() {

        // arrange
        int memoriaInicial = 3;
        int valorASerDividido = 3;
        int memoriaEsperada = 1;

        // act
        Calculadora calculadora = new Calculadora(memoriaInicial);
        calculadora.dividir(valorASerDividido);
        int memoriaObtida = calculadora.getMemoria();

        // asserts
        assertEquals(memoriaEsperada, memoriaObtida);
    }

        // 6.2
    @Test
    public void testarDividirNumeroInteiroNegativo() {

        // arrange
        int memoriaInicial = 3;
        int valorASerDividido = -3;
        int memoriaEsperada = -1;

        // act
        Calculadora calculadora = new Calculadora(memoriaInicial);
        calculadora.dividir(valorASerDividido);
        int memoriaObtida = calculadora.getMemoria();

        // asserts
        assertEquals(memoriaEsperada, memoriaObtida);
    }

            // 6.3
    @Test
    public void testarDividirPorZero() {

        // arrange
        int memoriaInicial = 3;
        int valorASerDividido = 0;
        int memoriaEsperada = -1;

        // act
        Calculadora calculadora = new Calculadora(memoriaInicial);
        calculadora.dividir(valorASerDividido);
        int memoriaObtida = calculadora.getMemoria();

        // asserts
        assertEquals(memoriaEsperada, memoriaObtida);
    }

                // 7.1
    @Test
    public void testarExponenciarPorUm() {

        // arrange
        int memoriaInicial = 3;
        int valorASerExponenciado = 1;
        int memoriaEsperada = 3;

        // act
        Calculadora calculadora = new Calculadora(memoriaInicial);
        calculadora.exponenciar(valorASerExponenciado);
        int memoriaObtida = calculadora.getMemoria();

        // asserts
        assertEquals(memoriaEsperada, memoriaObtida);
    }
    
    // 7.2
        @Test
    public void testarExponenciarPorDez() {

        // arrange
        int memoriaInicial = 3;
        int valorASerExponenciado = 10;
        int memoriaEsperada = 59049;

        // act
        Calculadora calculadora = new Calculadora(memoriaInicial);
        calculadora.exponenciar(valorASerExponenciado);
        int memoriaObtida = calculadora.getMemoria();

        // asserts
        assertEquals(memoriaEsperada, memoriaObtida);
    }

        // 7.3
        @Test
    public void testarExponenciarPorVinte() {

        // arrange
        int memoriaInicial = 3;
        int valorASerExponenciado = 20;
        int memoriaEsperada = 348678440;

        // act
        Calculadora calculadora = new Calculadora(memoriaInicial);
        calculadora.exponenciar(valorASerExponenciado);
        int memoriaObtida = calculadora.getMemoria();

        // asserts
        assertEquals(memoriaEsperada, memoriaObtida);
    }

            // 8.1
        @Test
    public void testarZerarMemoria() {

        // arrange
        int memoriaInicial = 3;
        int memoriaEsperada = 0;

        // act
        Calculadora calculadora = new Calculadora(memoriaInicial);
        calculadora.zerarMemoria();
        int memoriaObtida = calculadora.getMemoria();
        

        // asserts
        assertEquals(memoriaEsperada, memoriaObtida);
        
    }
}
    