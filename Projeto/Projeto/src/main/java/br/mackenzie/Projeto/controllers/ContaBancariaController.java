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

import br.mackenzie.Projeto.entities.ContaBancaria;
import br.mackenzie.Projeto.repositories.ContaBancariaRepository;

@RestController
@RequestMapping("/contasBancarias")
public class ContaBancariaController {
    @Autowired
    private ContaBancariaRepository repositorio;

    @GetMapping
    public List<ContaBancaria> getContaBancarias(@RequestParam Map<String,String> allParams) {
        List<ContaBancaria> contasBancarias;
    
        if (allParams.size() > 1 || (allParams.size() == 1 && !allParams.containsKey("nomeTitular"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parâmetro inválido");
        }
    
        String nomeTitular = allParams.get("nomeTitular");
    
        if (nomeTitular == null) {
            contasBancarias = repositorio.findAll();
        } else {
            contasBancarias = repositorio.findByNomeTitularContainingIgnoreCase(nomeTitular);
        }
    
        if (contasBancarias.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma conta bancária encontrada");
        } else {
            return contasBancarias;
        }
    }

    @GetMapping("/{id}")
    public ContaBancaria getContaBancariaById(@PathVariable Long id) {
        Optional<ContaBancaria> contaBancaria = repositorio.findById(id);

        if (contaBancaria.isPresent()) {
            return contaBancaria.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta Bancária não encontrada");
        }
    }

    @PostMapping
    public ContaBancaria setContaBancaria(@RequestBody ContaBancaria contaBancaria) {

        return repositorio.save(contaBancaria);
    }

    @PutMapping("/{id}")
    public ContaBancaria updateContaBancaria(@RequestBody ContaBancaria contaBancaria, @PathVariable Long id) {
        Optional<ContaBancaria> contaBancariaExistente = repositorio.findById(id);

        System.out.println(contaBancaria);
        System.out.println(id);

        if (contaBancariaExistente.isPresent()) {
            ContaBancaria contaBancariaEncontrado = contaBancariaExistente.get();

            contaBancariaEncontrado.setNomeTitular(contaBancaria.getNomeTitular());
            contaBancariaEncontrado.setSaldo(contaBancaria.getSaldo());
            contaBancariaEncontrado.setNmrAgencia(contaBancaria.getNmrAgencia());

            repositorio.save(contaBancariaEncontrado);

            return contaBancariaEncontrado;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta Bancária não encontrada");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteContaBancaria(@PathVariable Long id) {
        Optional<ContaBancaria> contaBancariaExistente = repositorio.findById(id);

        if (contaBancariaExistente.isPresent()) {
            repositorio.delete(contaBancariaExistente.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta Bancária não encontrada");
        }
    }

}
