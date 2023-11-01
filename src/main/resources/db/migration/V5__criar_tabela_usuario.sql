CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_registro DATE NOT NULL,
    emprestimo_id INT,
    FOREIGN KEY (emprestimo_id) REFERENCES emprestimo (id)
);