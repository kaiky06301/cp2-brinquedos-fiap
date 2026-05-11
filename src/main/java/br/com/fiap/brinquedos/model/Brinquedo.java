package br.com.fiap.brinquedos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "TDS_TB_Brinquedos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brinquedo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brinquedo_seq")
    @SequenceGenerator(name = "brinquedo_seq", sequenceName = "SEQ_BRINQUEDO", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "Tipo é obrigatório")
    @Size(min = 2, max = 50, message = "Tipo deve ter entre 2 e 50 caracteres")
    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @NotNull(message = "Classificação etária é obrigatória")
    @Min(value = 0, message = "Classificação mínima é 0 anos")
    @Max(value = 14, message = "Classificação máxima é 14 anos")
    @Column(name = "classificacao", nullable = false)
    private Integer classificacao;

    @NotBlank(message = "Tamanho é obrigatório")
    @Column(name = "tamanho", nullable = false, length = 20)
    private String tamanho;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    @Column(name = "preco", nullable = false)
    private Double preco;
}
