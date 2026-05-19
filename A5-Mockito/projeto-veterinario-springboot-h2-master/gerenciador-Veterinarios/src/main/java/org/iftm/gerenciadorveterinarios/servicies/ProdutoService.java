package org.iftm.gerenciadorveterinarios.servicies;

import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Produto;
import org.iftm.gerenciadorveterinarios.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Transactional
    public Produto cadastrar(Produto produto) {

        if (produto.getPreco() < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo");
        }

        produto.setAtivo(true);

        return repository.save(produto);
    }

    @Transactional
    public Produto inativar(Integer id) {

        Optional<Produto> produtoOptional = repository.findById(id);

        if (!produtoOptional.isPresent()) {
            throw new RuntimeException("Produto não encontrado");
        }

        Produto produto = produtoOptional.get();

        produto.setAtivo(false);

        return repository.save(produto);
    }
}
