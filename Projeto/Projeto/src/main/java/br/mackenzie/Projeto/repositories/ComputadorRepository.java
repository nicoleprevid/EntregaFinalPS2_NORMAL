// Felipe Damasceno - 42202817
// Hyandra Marjory - 42217954
// Nicole Previd - 42206774

package br.mackenzie.Projeto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.mackenzie.Projeto.entities.Computador;

public interface ComputadorRepository extends JpaRepository<Computador, Long> {
    public List<Computador> findByMarcaContainingIgnoreCase(String marca);
    public List<Computador> findByMarcaOrId(String marca, Long id);
}
