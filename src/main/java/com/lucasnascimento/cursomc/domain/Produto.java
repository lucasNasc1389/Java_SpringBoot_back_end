package com.lucasnascimento.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Produto implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String nome;
  private Double preco;

  @ManyToMany
  // Nome da tabela de relacionamento entre Produto e Categoria
  @JoinTable(name = "PRODUTO_CATEGORIA",
      // Coluna contendo foreignKey correspondente a tabela Produto
      joinColumns = @JoinColumn(name = "produto_id"),
      // Coluna contendo foreignKey correspondente a tabela Categoriase
      inverseJoinColumns = @JoinColumn(name = "categoria_id"))
  @JsonIgnore
  private List<Categoria> categorias = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "id.produto")
  private Set<ItemPedido> itens = new HashSet<>();

  public Produto() {

  }

  public Produto(Integer id, String nome, Double preco){
    this.id = id;
    this.nome = nome;
    this.preco = preco;
  }

  @JsonIgnore
  public List<Pedido> getPedidos(){
    List<Pedido> lista = new ArrayList<>();

    itens.forEach(itemPedido -> lista.add(itemPedido.getPedido()));

//    for(ItemPedido x : itens) {
//      lista.add(x.getPedido());
//    }

    return lista;
  }

  public Integer getId() {
    return id;
  }

  public Produto setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getNome() {
    return nome;
  }

  public Produto setNome(String nome) {
    this.nome = nome;
    return this;
  }

  public Double getPreco() {
    return preco;
  }

  public Produto setPreco(Double preco) {
    this.preco = preco;
    return this;
  }

  public List<Categoria> getCategorias() {
    return categorias;
  }

  public Produto setCategorias(
      List<Categoria> categorias) {
    this.categorias = categorias;
    return this;
  }

  public Set<ItemPedido> getItens() {
    return itens;
  }

  public Produto setItens(Set<ItemPedido> itens) {
    this.itens = itens;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Produto)) {
      return false;
    }

    Produto produto = (Produto) o;

    return getId().equals(produto.getId());
  }

  @Override
  public int hashCode() {
    return getId().hashCode();
  }
}
