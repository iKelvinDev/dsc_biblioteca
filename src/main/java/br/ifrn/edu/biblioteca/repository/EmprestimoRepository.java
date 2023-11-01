package br.ifrn.edu.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifrn.edu.biblioteca.domain.emprestimo.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    
}
