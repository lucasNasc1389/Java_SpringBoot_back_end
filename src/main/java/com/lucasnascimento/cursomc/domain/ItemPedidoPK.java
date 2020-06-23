package com.lucasnascimento.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class  ItemPedidoPK implements Serializable {
  private static final long serialVersionUID = 1L;

  @ManyToOne
  @JoinColumn(name="pedido_id")
  private Pedido pedido;

  @ManyToOne
  @JoinColumn(name="produto_id")
  private Produto produto;

  public Pedido getPedido() {
    return pedido;
  }

  public ItemPedidoPK setPedido(Pedido pedido) {
    this.pedido = pedido;
    return this;
  }

  public Produto getProduto() {
    return produto;
  }

  public ItemPedidoPK setProduto(Produto produto) {
    this.produto = produto;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ItemPedidoPK)) {
      return false;
    }
    ItemPedidoPK that = (ItemPedidoPK) o;
    return Objects.equals(getPedido(), that.getPedido()) &&
        Objects.equals(getProduto(), that.getProduto());
  }

  @Override
  public int hashCode() {
     return Objects.hash(getPedido(), getProduto());
  }
}
