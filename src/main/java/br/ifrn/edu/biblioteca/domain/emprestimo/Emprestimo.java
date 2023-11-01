package br.ifrn.edu.biblioteca.domain.emprestimo;

import java.time.LocalDate;

import br.ifrn.edu.biblioteca.domain.livro.Livro;
import br.ifrn.edu.biblioteca.domain.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "emprestimo")
@Table(name = "emprestimo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucao;

    @OneToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
