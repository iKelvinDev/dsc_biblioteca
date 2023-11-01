package br.ifrn.edu.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifrn.edu.biblioteca.domain.livro.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    
}
