package br.ifrn.edu.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.ifrn.edu.biblioteca.domain.livro.Livro;
import br.ifrn.edu.biblioteca.repository.LivroRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("livros")
public class LivroController {

    @Autowired
    private LivroRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Livro> cadastrar(@RequestBody @Valid Livro livro, 
                                            UriComponentsBuilder uriBuilder){
        Livro livroLocal = repository.save(livro);
        var uri = uriBuilder.path("/livros/{id}").
                  buildAndExpand(livroLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> detalhar(@PathVariable Long id){
        var livro = repository.getReferenceById(id);
        return ResponseEntity.ok(livro);
    }

    @GetMapping
    public ResponseEntity<Page<Livro>> listar(@PageableDefault(size=4, 
                                        sort = {"titulo"}) Pageable paginacao){
        var livros = repository.findAll(paginacao);
        return ResponseEntity.ok(livros);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Livro> excluir(@PathVariable Long id){
        var livro = repository.getReferenceById(id);
        repository.delete(livro);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Livro> atualizar(@RequestBody @Valid Livro livro){
        Livro livroLocal = repository.findById(livro.getId()).get();
 
        livroLocal.setTitulo(livro.getTitulo());

        return ResponseEntity.ok(livroLocal);
    }
    
}
