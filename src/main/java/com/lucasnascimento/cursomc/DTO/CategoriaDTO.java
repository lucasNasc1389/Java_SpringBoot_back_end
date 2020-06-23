package com.lucasnascimento.cursomc.DTO;

import com.lucasnascimento.cursomc.domain.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoriaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "O preenchimento do campo é obrigatório.")
    @Length(min=5, max=80, message = "O nome da categoria deve ter entre 5 e 20 caracteres.")
    private String nome;

    public CategoriaDTO(){}

    public CategoriaDTO(Categoria obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
    }

    public Integer getId() {
        return id;
    }

    public CategoriaDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public CategoriaDTO setNome(String nome) {
        this.nome = nome;
        return this;
    }
}
