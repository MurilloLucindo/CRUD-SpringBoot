package com.example.demo.ControllerPessoa;

import com.example.demo.Model.Pessoa;
import com.example.demo.Repository.PessoaRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaRepository repository;

    public PessoaController(PessoaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Pessoa> cadastrar(@RequestBody Pessoa pessoa) {

    Pessoa novaPessoa = repository.save(pessoa);

    return ResponseEntity.ok(novaPessoa);
}

    
@PutMapping("/{id}")
public ResponseEntity<?> atualizar(
        @PathVariable Long id,
        @RequestBody Pessoa dados) {

    Pessoa pessoa = repository.findById(id).orElse(null);

    if (pessoa == null) {
        return ResponseEntity.notFound().build();
    }

    pessoa.setNome(dados.getNome());

    repository.save(pessoa);

    return ResponseEntity.ok(pessoa);
}


    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(
        @PathVariable Long id,
        @RequestBody Map<String, Object> dados) {

    Pessoa pessoa = repository.findById(id).orElse(null);

    if (pessoa == null) {
        return ResponseEntity.notFound().build();
    }

    if (dados.containsKey("nome")) {
        pessoa.setNome((String) dados.get("nome"));
    }

    repository.save(pessoa);

    return ResponseEntity.ok(pessoa);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
