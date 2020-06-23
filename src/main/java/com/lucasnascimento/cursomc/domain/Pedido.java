package com.lucasnascimento.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern="dd/MM/yyyy HH:mm")
  private Date data;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
  private Pagamento pagamento;


  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  @ManyToOne
  @JoinColumn(name = "endereco_de_entrega_id")
  private Endereco enderecoDeEntrega;

  @OneToMany(mappedBy = "id.pedido")
  private Set<ItemPedido> itens = new HashSet<>();

  public Pedido() {

  }

  public Pedido(Integer id, Date data,
      Cliente cliente, Endereco enderecoDeEntrega) {
    this.id = id;
    this.data = data;
    this.cliente = cliente;
    this.enderecoDeEntrega = enderecoDeEntrega;
  }

  public Integer getId() {
    return id;
  }

  public Pedido setId(Integer id) {
    this.id = id;
    return this;
  }

  public Date getData() {
    return data;
  }

  public Pedido setData(Date data) {
    this.data = data;
    return this;
  }

  public Pagamento getPagamento() {
    return pagamento;
  }

  public Pedido setPagamento(Pagamento pagamento) {
    this.pagamento = pagamento;
    return this;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public Pedido setCliente(Cliente cliente) {
    this.cliente = cliente;
    return this;
  }

  public Endereco getEnderecoDeEntrega() {
    return enderecoDeEntrega;
  }

  public Pedido setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
    this.enderecoDeEntrega = enderecoDeEntrega;
    return this;
  }

  public Set<ItemPedido> getItens() {
    return itens;
  }

  public Pedido setItens(Set<ItemPedido> itens) {
    this.itens = itens;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Pedido)) {
      return false;
    }
    Pedido pedido = (Pedido) o;
    return Objects.equals(getId(), pedido.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
