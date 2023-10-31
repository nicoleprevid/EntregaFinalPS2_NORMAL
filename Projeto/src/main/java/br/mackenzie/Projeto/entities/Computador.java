// Felipe Damasceno - 42202817
// Hyandra Marjory - 42217954
// Nicole Previd - 42206774

package br.mackenzie.Projeto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Computador {
    @Id
    @GeneratedValue
    private Long id;
    private String marca;
    private String processador;
    private String quantidadeRAM;
    private String tamanhoDisco;
}
