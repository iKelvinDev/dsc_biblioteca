package br.ifrn.edu.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifrn.edu.biblioteca.domain.autor.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    
}
