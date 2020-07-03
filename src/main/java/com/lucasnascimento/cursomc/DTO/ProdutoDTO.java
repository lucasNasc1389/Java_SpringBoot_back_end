package com.lucasnascimento.cursomc.DTO;

import com.lucasnascimento.cursomc.domain.Categoria;
import com.lucasnascimento.cursomc.domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDTO {

    private Integer id;
    private String nome;
    private List<Categoria> categorias = new ArrayList<>();

    public ProdutoDTO() {}

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
    }

    public Integer getId() {
        return id;
    }

    public ProdutoDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public ProdutoDTO setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public ProdutoDTO setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
        return this;
    }
}
