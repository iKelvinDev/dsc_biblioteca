package br.ifrn.edu.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.ifrn.edu.biblioteca.domain.emprestimo.Emprestimo;
import br.ifrn.edu.biblioteca.repository.EmprestimoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Emprestimo> cadastrar(@RequestBody @Valid Emprestimo emprestimo, 
                                            UriComponentsBuilder uriBuilder){
        Emprestimo emprestimoLocal = repository.save(emprestimo);
        var uri = uriBuilder.path("/emprestimos/{id}").
                  buildAndExpand(emprestimoLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> detalhar(@PathVariable Long id){
        var emprestimo = repository.getReferenceById(id);
        return ResponseEntity.ok(emprestimo);
    }

    @GetMapping
    public ResponseEntity<Page<Emprestimo>> listar(Pageable paginacao){
        var emprestimos = repository.findAll(paginacao);
        return ResponseEntity.ok(emprestimos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Emprestimo> excluir(@PathVariable Long id){
        var emprestimo = repository.getReferenceById(id);
        repository.delete(emprestimo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Emprestimo> atualizar(@RequestBody @Valid Emprestimo emprestimo){
        Emprestimo emprestimoLocal = repository.findById(emprestimo.getId()).get();
 
        emprestimoLocal.setLivro(emprestimo.getLivro());

        return ResponseEntity.ok(emprestimoLocal);
    }
    
}
