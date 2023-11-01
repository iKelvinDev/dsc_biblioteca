package br.ifrn.edu.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifrn.edu.biblioteca.domain.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
