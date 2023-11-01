CREATE TABLE emprestimo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_emprestimo DATE,
    data_devolucao DATE NOT NULL,
    livro_id INT,
    emprestimo_id INT,
    FOREIGN KEY (livro_id) REFERENCES livro (id),
    FOREIGN KEY (emprestimo_id) REFERENCES emprestimo (id)
);