package com.serratec.livro.controller;

import com.serratec.livro.model.Livro;
import com.serratec.livro.repository.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gerenciar Livros.
 * Endpoints:
 * GET    /livros        -> lista todos
 * GET    /livros/{id}   -> busca por id
 * POST   /livros        -> cria novo
 * PUT    /livros/{id}   -> atualiza existente
 * DELETE /livros/{id}   -> remove
 */
@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository repository;

    // GET /livros
    @GetMapping
    public List<Livro> listar() {
        return repository.findAll();
    }

    // GET /livros/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        Optional<Livro> op = repository.findById(id);
        return op.map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
    }

    // POST /livros
    // @Valid garante que as anotações de validação na entidade sejam verificadas
    @PostMapping
    public ResponseEntity<Livro> criar(@Valid @RequestBody Livro livro) {
        Livro salvo = repository.save(livro);
        // cria URI do recurso criado (ex: /livros/5)
        URI uri = URI.create("/livros/" + salvo.getId());
        return ResponseEntity.created(uri).body(salvo);
    }

    // PUT /livros/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id,
                                           @Valid @RequestBody Livro livroAtualizado) {
        return repository.findById(id)
                .map(l -> {
                    // atualiza campos permitidos
                    l.setTitulo(livroAtualizado.getTitulo());
                    l.setQtdPaginas(livroAtualizado.getQtdPaginas());
                    l.setPublicacao(livroAtualizado.getPublicacao());
                    Livro salvo = repository.save(l);
                    return ResponseEntity.ok(salvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /livros/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
