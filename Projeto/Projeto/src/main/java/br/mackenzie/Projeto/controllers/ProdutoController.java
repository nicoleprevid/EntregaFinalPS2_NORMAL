// Felipe Damasceno - 42202817
// Hyandra Marjory - 42217954
// Nicole Previd - 42206774

package br.mackenzie.Projeto.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.mackenzie.Projeto.entities.Produto;
import br.mackenzie.Projeto.repositories.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repositorio;

    @GetMapping
    public List<Produto> getProdutos(@RequestParam Map<String, String> allParams) {
        List<Produto> produtos;

        if (allParams.size() > 1 || (allParams.size() == 1 && !allParams.containsKey("descricao"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parâmetro inválido");
        }

        String descricao = allParams.get("descricao");

        if (descricao == null) {
            produtos = repositorio.findAll();
        } else {
            produtos = repositorio.findByDescricaoContainingIgnoreCase(descricao);
        }

        if (produtos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum produto encontrado");
        } else {
            return produtos;
        }
    }

    @GetMapping("/{id}")
    public Produto getProdutoById(@PathVariable Long id) {
        Optional<Produto> produto = repositorio.findById(id);

        if (produto.isPresent()) {
            return produto.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
    }

    @PostMapping
    public Produto setProduto(@RequestBody Produto produto) {

        return repositorio.save(produto);
    }
    

    @PutMapping("/{id}")
    public Produto updateProduto(@RequestBody Produto produto, @PathVariable Long id) {
        Optional<Produto> produtoExistente = repositorio.findById(id);

        System.out.println(produto);
        System.out.println(id);

        if (produtoExistente.isPresent()) {
            Produto produtoEncontrado = produtoExistente.get();

            produtoEncontrado.setDescricao(produto.getDescricao());
            produtoEncontrado.setMarca(produto.getMarca());
            produtoEncontrado.setPreco(produto.getPreco());

            repositorio.save(produtoEncontrado);

            return produtoEncontrado;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id) {
        Optional<Produto> produtoExistente = repositorio.findById(id);

        if (produtoExistente.isPresent()) {
            repositorio.delete(produtoExistente.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
    }

}
