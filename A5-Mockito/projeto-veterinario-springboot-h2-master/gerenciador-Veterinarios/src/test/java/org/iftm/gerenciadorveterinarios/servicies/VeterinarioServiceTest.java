package org.iftm.gerenciadorveterinarios.servicies;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

@ExtendWith(MockitoExtension.class)
public class VeterinarioServiceTest {

    // cria a simulação/mock de todas as classes que serão injetadas na classe a ser
    // testada
    @Mock
    private VeterinarioRepository repositorio;

    // classe a ser testada que receberá todas as injeções de classes mock
    @InjectMocks
    private VeterinarioService service;

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
        Veterinario veterinarioEsperado = new Veterinario(null, nomeExistente, "", "", BigDecimal.valueOf(0));

        // configurar o mock - criando o cenário de teste
        when(repositorio.findById(Mockito.anyInt())).thenReturn(Optional.of(veterinarioEsperado));

        // act
        Optional<Veterinario> resposta = service.buscaVeterinariosPeloId(Mockito.anyInt());
        Veterinario veterinarioRetornado = resposta.get();

        // assert
        assertTrue(resposta.isPresent());
        assertEquals(tamanhoEsperadoNome, veterinarioRetornado.getNome().length());
        assertEquals(nomeEsperado, veterinarioRetornado.getNome());

        verify(repositorio).findById(Mockito.anyInt());
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

        verify(repositorio).delete(veterinarioExcluido);

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

        when(repositorio.findByNomeContains("Silva")).thenReturn(listaVeterinario);

        // Act

        List<Veterinario> respostaObtida = service.buscaVeterinariosComParteNome("Silva");

        // Assert

        assertTrue(listaVeterinario.size() == respostaObtida.size());
        assertEquals(listaVeterinario.get(0), respostaObtida.get(0));
        assertEquals(listaVeterinario.get(1), respostaObtida.get(1));

        // Verify

        verify(repositorio).findByNomeContains(Mockito.any());

    }

    @Test
    public void deveLancarExcecaoAoApagarQuandoIdNaoExistir() {
        // Arrange
        Integer id = 1;

        when(repositorio.findById(id))
                .thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(RuntimeException.class, () -> {
            service.apagarVeterinario(id);
        });

        // Verify
        verify(repositorio, never()).delete(Mockito.any());
    }

}
