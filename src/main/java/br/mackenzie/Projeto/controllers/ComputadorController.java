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

import br.mackenzie.Projeto.entities.Computador;
import br.mackenzie.Projeto.repositories.ComputadorRepository;

@RestController
@RequestMapping("/computadores")
public class ComputadorController {
    @Autowired
    private ComputadorRepository repositorio;

    @GetMapping
    public List<Computador> getComputadores(@RequestParam Map<String,String> allParams) {
        List<Computador> computadores;
    
        if (allParams.size() > 1 || (allParams.size() == 1 && !allParams.containsKey("marca"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parâmetro inválido");
        }
    
        String marca = allParams.get("marca");
    
        if (marca == null) {
            computadores = repositorio.findAll();
        } else {
            computadores = repositorio.findByMarcaContainingIgnoreCase(marca);
        }
    
        if (computadores.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum computador encontrado");
        } else {
            return computadores;
        }
    }

    @GetMapping("/{id}")
    public Computador getComputadorById(@PathVariable Long id) {
        Optional<Computador> computador = repositorio.findById(id);

        if (computador.isPresent()) {
            return computador.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Computador não encontrado");
        }
    }

    @PostMapping
    public Computador setComputador(@RequestBody Computador computador) {
        return repositorio.save(computador);
    }

    @PutMapping("/{id}")
    public Computador updateComputador(@RequestBody Computador computador, @PathVariable Long id) {
        Optional<Computador> computadorExistente = repositorio.findById(id);

        if (computadorExistente.isPresent()) {
            Computador computadorEncontrado = computadorExistente.get();
            computadorEncontrado.setMarca(computador.getMarca());
            computadorEncontrado.setProcessador(computador.getProcessador());
            computadorEncontrado.setQuantidadeRAM(computador.getQuantidadeRAM());
            computadorEncontrado.setTamanhoDisco(computador.getTamanhoDisco());
            repositorio.save(computadorEncontrado);
            return computadorEncontrado;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Computador não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteComputador(@PathVariable Long id) {
        Optional<Computador> computadorExistente = repositorio.findById(id);

        if (computadorExistente.isPresent()) {
            repositorio.delete(computadorExistente.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Computador não encontrado");
        }
    }
}
