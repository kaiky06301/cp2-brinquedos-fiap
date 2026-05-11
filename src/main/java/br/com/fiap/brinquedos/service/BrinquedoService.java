package br.com.fiap.brinquedos.service;

import br.com.fiap.brinquedos.model.Brinquedo;
import br.com.fiap.brinquedos.repository.BrinquedoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrinquedoService {

    @Autowired
    private BrinquedoRepository brinquedoRepository;

    // CREATE - Salva novo brinquedo no BD
    public Brinquedo salvar(Brinquedo brinquedo) {
        return brinquedoRepository.save(brinquedo);
    }

    // READ - Lista todos os brinquedos
    public List<Brinquedo> listarTodos() {
        return brinquedoRepository.findAll();
    }

    // READ - Busca brinquedo por ID
    public Optional<Brinquedo> buscarPorId(Long id) {
        return brinquedoRepository.findById(id);
    }

    // READ - Busca por nome
    public List<Brinquedo> buscarPorNome(String nome) {
        return brinquedoRepository.findByNomeContainingIgnoreCase(nome);
    }

    // UPDATE - Atualiza brinquedo existente
    public Optional<Brinquedo> atualizar(Long id, Brinquedo brinquedoAtualizado) {
        return brinquedoRepository.findById(id).map(brinquedoExistente -> {
            brinquedoExistente.setNome(brinquedoAtualizado.getNome());
            brinquedoExistente.setTipo(brinquedoAtualizado.getTipo());
            brinquedoExistente.setClassificacao(brinquedoAtualizado.getClassificacao());
            brinquedoExistente.setTamanho(brinquedoAtualizado.getTamanho());
            brinquedoExistente.setPreco(brinquedoAtualizado.getPreco());
            return brinquedoRepository.save(brinquedoExistente);
        });
    }

    // DELETE - Remove brinquedo por ID
    public boolean deletar(Long id) {
        if (brinquedoRepository.existsById(id)) {
            brinquedoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
