ALTER TABLE autor
ADD CONSTRAINT fk_livro_autor FOREIGN KEY (livro_id) REFERENCES livro (id)