package com.lucasnascimento.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasnascimento.cursomc.domain.enums.EstadoPagamento;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private Integer id;

  private Integer estado;

  @JsonIgnore
  @JoinColumn(name = "pedido_id")
  @OneToOne
  @MapsId
  private Pedido pedido;


  public Pagamento(){

  }

  public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
    super();
    this.id = id;
    this.estado = estado.getCodigo();
    this.pedido = pedido;
  }

  public Integer getId() {
    return id;
  }

  public Pagamento setId(Integer id) {
    this.id = id;
    return this;
  }

  public EstadoPagamento getEstado() {
    return EstadoPagamento.toEnum(estado);
  }

  public Pagamento setEstado(Integer estado) {
    this.estado = estado;
    return this;
  }

  public Pedido getPedido() {
    return pedido;
  }

  public Pagamento setPedido(Pedido pedido) {
    this.pedido = pedido;
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
    Pagamento pagamento = (Pagamento) o;
    return id.equals(pagamento.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
