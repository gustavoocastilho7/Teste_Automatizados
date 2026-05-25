package org.iftm.gerenciadorveterinarios.servicies;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Produto;
import org.iftm.gerenciadorveterinarios.repositories.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoService service;

    @Test
    public void deveCadastrarProdutoComoAtivo() {

        // Arrange
        Produto produto = new Produto();

        produto.setDescricao("Notebook");
        produto.setPreco(3500.0);
        produto.setAtivo(false);

        when(repository.save(any()))
                .thenReturn(produto);

        // Act
        Produto resposta = service.cadastrar(produto);

        // Assert
        assertTrue(resposta.getAtivo());

        verify(repository).save(produto);
    }


    @Test
    public void deveLancarExcecaoQuandoPrecoForNegativo() {

        // Arrange
        Produto produto = new Produto();

        produto.setDescricao("Notebook");
        produto.setPreco(-500.0);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(produto);
        });

        verify(repository, never()).save(any());
    }


    @Test
    public void deveInativarProduto() {

        // Arrange
        Produto produto = new Produto();

        produto.setId(1);
        produto.setDescricao("Notebook");
        produto.setAtivo(true);

        when(repository.findById(1))
                .thenReturn(Optional.of(produto));

        when(repository.save(any()))
                .thenReturn(produto);

        // Act
        Produto resposta = service.inativar(1);

        // Assert
        assertFalse(resposta.getAtivo());

        verify(repository).findById(1);
        verify(repository).save(produto);
    }
}
