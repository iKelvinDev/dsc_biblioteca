CREATE TABLE livro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    isbn VARCHAR (20) NOT NULL,
    ano_publicacao DATE NOT NULL,
    quantidade INT NOT NULL,
    livro_id INT,
    autor_id INT,
    FOREIGN KEY (autor_id) REFERENCES autor (id)
);