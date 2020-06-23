package com.lucasnascimento.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ItemPedido implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonIgnore
  @EmbeddedId
  private ItemPedidoPK id = new ItemPedidoPK();

  private Double desconto;
  private Integer quantidade;
  private Double preco;

  public ItemPedido() {
  }

  public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer
      quantidade, Double preco) {
    super();
    id.setPedido(pedido);
    id.setProduto(produto);
    this.desconto = desconto;
    this.quantidade = quantidade;
    this.preco = preco;
  }

  @JsonIgnore
  public Pedido getPedido() {
    return id.getPedido();
  }

  public Produto getProduto() {
    return id.getProduto();
  }

  public ItemPedidoPK getId() {
    return id;
  }

  public ItemPedido setId(ItemPedidoPK id) {
    this.id = id;
    return this;
  }

  public Double getDesconto() {
    return desconto;
  }

  public ItemPedido setDesconto(Double desconto) {
    this.desconto = desconto;
    return this;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public ItemPedido setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
    return this;
  }

  public Double getPreco() {
    return preco;
  }

  public ItemPedido setPreco(Double preco) {
    this.preco = preco;
    return this;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ItemPedido)) {
      return false;
    }
    ItemPedido that = (ItemPedido) o;
    return Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
