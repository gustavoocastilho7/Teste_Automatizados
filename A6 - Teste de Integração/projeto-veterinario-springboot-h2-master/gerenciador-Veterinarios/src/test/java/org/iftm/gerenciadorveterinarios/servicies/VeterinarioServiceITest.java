package org.iftm.gerenciadorveterinarios.servicies;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.iftm.gerenciadorveterinarios.repositories.VeterinarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VeterinarioServiceITest {

    // classe a ser testada que receberá todas as injeções de classes mock
    @Autowired
    private VeterinarioService service;

    @Autowired
    private VeterinarioRepository repository;

    /*
     * Validar se a busca por veterinario retorna o veterinário correto e o nome com
     * no máximo 10 caracteres.
     */
    @Test
    public void testarBuscarVeterinarioPorIDExistenteRetornaVeterinarioComTruncado() {
        // arrange
        String nomeExistente = "Erica Queiroz Pinto";
        int tamanhoEsperadoNome = 10;
        String nomeEsperado = "Erica Quei";
        int idExistente = 2;

        // configurar o mock - criando o cenário de teste

        // act
        Optional<Veterinario> resposta = service.buscaVeterinariosPeloId(idExistente);
        Veterinario veterinarioRetornado = resposta.get();

        // assert
        assertTrue(resposta.isPresent());
        assertEquals(tamanhoEsperadoNome, veterinarioRetornado.getNome().length());
        assertEquals(nomeEsperado, veterinarioRetornado.getNome());

    }

    @Test
    public void testarApagarRealmenteApagaRegistro() {
        // Arrange
        Integer idExistente = 2;
        String nomeExistente = "Erica Queiroz Pinto";
        Veterinario veterinarioExcluido = new Veterinario(idExistente, nomeExistente, "", "", null);

        // act and assert
        assertDoesNotThrow(
                () -> {
                    service.apagar(veterinarioExcluido);
                });

    }

    @Test
    public void buscaVeterinariosComParteNome() {
        // Arrange
        Veterinario veterinario1 = new Veterinario();
        Veterinario veterinario2 = new Veterinario();

        veterinario1.setNome("Gabriel Silva");
        veterinario2.setNome("Gustavo Silva");

        List<Veterinario> listaVeterinario = new ArrayList<>();
        listaVeterinario.add(veterinario1);
        listaVeterinario.add(veterinario2);

        // Act

        List<Veterinario> respostaObtida = service.buscaVeterinariosComParteNome("Quei");

        // Assert

        assertTrue(listaVeterinario.size() == respostaObtida.size());
        assertEquals(listaVeterinario.get(0), respostaObtida.get(0));
        assertEquals(listaVeterinario.get(1), respostaObtida.get(1));

    }

    @Test
    public void deveLancarExcecaoAoApagarQuandoIdNaoExistir() {
        // Arrange
        Integer id = 1;

        // Act + Assert
        assertThrows(RuntimeException.class, () -> {
            service.apagarVeterinario(id);
        });

    }

    @Test
    void deveBuscarVeterinarioPorIdComSucessoComLimiteCaracteres() {
        // Arrange
        Integer idExistente = 1;
        // Act - Chamada real que vai bater lá na tabela do H2
        Optional<Veterinario> resultado = service.buscaVeterinariosPeloId(idExistente);
        // Assert
        assertTrue(resultado.isPresent());
        // verifica a regra de negocio de retornar apenas os 10 primeiros caracteres.
        assertEquals("Conceição ", resultado.get().getNome());

    }

    @Test
    void deveSalvarVeterinarioNoBancoDeDados() {
        // Arrange - Objeto novo, sem ID (nulo)
        Veterinario novoVet = new Veterinario(null, "Dra. Marcia", "marcia@gmail.com", "Grandes Animais",
                BigDecimal.valueOf(5500.0));
        // Act
        Veterinario salvo = service.salvar(novoVet);
        // Assert
        assertNotNull(salvo.getId(), "O banco H2 deveria ter gerado um ID automático!");
        assertEquals("Dra. Marcia", salvo.getNome());
        // Prova Real: Usando o repository para checar se ele está gravado de verdade
        Optional<Veterinario> vetNoBanco = repository.findById(salvo.getId());
        assertTrue(vetNoBanco.isPresent());
        assertEquals("marcia@gmail.com", vetNoBanco.get().getEmail());
    }

    @Test
void deveLancarExcecaoAoApagarIdNaoExistente() {
// Arrange
Integer idInexistente = 9999; // ID que não mapeamos no import.sql
int quantidadeOriginal = 2;
// Act & Assert
RuntimeException e = assertThrows(RuntimeException.class, () -> {
service.apagarVeterinario(idInexistente);
});
int quantidadeAtual = service.buscaTodosVeterinarios().size();
assertEquals("Veterinário com ID 9999 não foi encontrado no banco de dados. Operação de exclusão cancelada.", e.getMessage());
// Pergunta: "Como garantir que o banco não apagou nada errado?"
// Podemos checar se a contagem de registros continua igual!
assertEquals(quantidadeOriginal, quantidadeAtual);
}

}
