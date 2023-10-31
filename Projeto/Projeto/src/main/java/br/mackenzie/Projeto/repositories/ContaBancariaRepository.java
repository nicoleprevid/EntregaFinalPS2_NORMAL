// Felipe Damasceno - 42202817
// Hyandra Marjory - 42217954
// Nicole Previd - 42206774

package br.mackenzie.Projeto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.mackenzie.Projeto.entities.ContaBancaria;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {
    public List<ContaBancaria> findByNomeTitularContainingIgnoreCase(String nomeTitular);
    public List<ContaBancaria> findByNomeTitularOrId(String nomeTitular, Long id);
}

