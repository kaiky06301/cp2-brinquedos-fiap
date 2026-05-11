package br.com.fiap.brinquedos.controller;

import br.com.fiap.brinquedos.model.Brinquedo;
import br.com.fiap.brinquedos.service.BrinquedoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brinquedos")
public class BrinquedoController {

    @Autowired
    private BrinquedoService brinquedoService;

    // POST /brinquedos - Cadastrar novo brinquedo
    @PostMapping
    public ResponseEntity<Brinquedo> criar(@Valid @RequestBody Brinquedo brinquedo) {
        Brinquedo salvo = brinquedoService.salvar(brinquedo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // GET /brinquedos - Listar todos os brinquedos
    @GetMapping
    public ResponseEntity<List<Brinquedo>> listarTodos() {
        List<Brinquedo> brinquedos = brinquedoService.listarTodos();
        return ResponseEntity.ok(brinquedos);
    }

    // GET /brinquedos/{id} - Buscar brinquedo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Brinquedo> buscarPorId(@PathVariable Long id) {
        return brinquedoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /brinquedos/buscar?nome=xxx - Buscar brinquedo por nome
    @GetMapping("/buscar")
    public ResponseEntity<List<Brinquedo>> buscarPorNome(@RequestParam String nome) {
        List<Brinquedo> brinquedos = brinquedoService.buscarPorNome(nome);
        if (brinquedos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(brinquedos);
    }

    // PUT /brinquedos/{id} - Atualizar brinquedo
    @PutMapping("/{id}")
    public ResponseEntity<Brinquedo> atualizar(@PathVariable Long id,
                                               @Valid @RequestBody Brinquedo brinquedo) {
        return brinquedoService.atualizar(id, brinquedo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /brinquedos/{id} - Excluir brinquedo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (brinquedoService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
