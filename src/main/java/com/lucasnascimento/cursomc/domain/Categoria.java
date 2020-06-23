package com.lucasnascimento.cursomc.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Categoria implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  private String nome;

  @ManyToMany(mappedBy = "categorias")
  private List<Produto> produtos = new ArrayList<>();


  public Categoria() {
  }

  public Categoria(Integer id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  public Integer getId() {
    return id;
  }

  public Categoria setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getNome() {
    return nome;
  }

  public Categoria setNome(String nome) {
    this.nome = nome;
    return this;
  }

  public List<Produto> getProdutos() {
    return produtos;
  }

  public Categoria setProdutos(List<Produto> produtos) {
    this.produtos = produtos;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Categoria categoria = (Categoria) o;
    return id.equals(categoria.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
