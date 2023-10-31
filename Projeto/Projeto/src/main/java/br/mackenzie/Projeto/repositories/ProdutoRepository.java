// Felipe Damasceno - 42202817
// Hyandra Marjory - 42217954
// Nicole Previd - 42206774

package br.mackenzie.Projeto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.mackenzie.Projeto.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    public List<Produto> findByDescricaoContainingIgnoreCase(String descricao);
    public List<Produto> findByDescricaoOrId(String descricao, Long id);
}
