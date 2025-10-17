package com.serratec.livro.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Embeddable
public class Publicacao {

    @NotBlank(message = "O autor é obrigatório.")
    @Size(max = 25, message = "O autor não pode ultrapassar 25 caracteres.")
    private String autor;

    private LocalDate dataPublicacao;

    private String editora;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }
}
