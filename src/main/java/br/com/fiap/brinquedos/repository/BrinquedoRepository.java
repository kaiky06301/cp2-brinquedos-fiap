package br.com.fiap.brinquedos.repository;

import br.com.fiap.brinquedos.model.Brinquedo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrinquedoRepository extends JpaRepository<Brinquedo, Long> {

    // Busca por nome contendo o texto (case insensitive)
    List<Brinquedo> findByNomeContainingIgnoreCase(String nome);

    // Busca por tipo
    List<Brinquedo> findByTipoIgnoreCase(String tipo);

    // Busca por faixa etária
    List<Brinquedo> findByClassificacaoLessThanEqual(Integer classificacao);
}
