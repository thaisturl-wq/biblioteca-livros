package com.serratec.livro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.serratec.livro.model.Livro;

/**
 * Interface de repositório que fornece operações CRUD automaticamente.
 */
public interface LivroRepository extends JpaRepository<Livro, Long> {
    // métodos CRUD (save, findAll, findById, deleteById, etc.) já disponíveis
}
