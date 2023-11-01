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

import br.ifrn.edu.biblioteca.domain.usuario.Usuario;
import br.ifrn.edu.biblioteca.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid Usuario usuario, 
                                            UriComponentsBuilder uriBuilder){
        Usuario usuarioLocal = repository.save(usuario);
        var uri = uriBuilder.path("/usuarios/{id}").
                  buildAndExpand(usuarioLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> detalhar(@PathVariable Long id){
        var usuario = repository.getReferenceById(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<Page<Usuario>> listar(@PageableDefault(size=4, 
                                        sort = {"nome"}) Pageable paginacao){
        var usuarios = repository.findAll(paginacao);
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Usuario> excluir(@PathVariable Long id){
        var usuario = repository.getReferenceById(id);
        repository.delete(usuario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Usuario> atualizar(@RequestBody @Valid Usuario usuario){
        Usuario usuarioLocal = repository.findById(usuario.getId()).get();
 
        usuarioLocal.setNome(usuario.getNome());

        return ResponseEntity.ok(usuarioLocal);
    }
    
}
