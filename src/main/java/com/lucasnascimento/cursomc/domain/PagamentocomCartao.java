package com.lucasnascimento.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.lucasnascimento.cursomc.domain.enums.EstadoPagamento;
import java.util.Objects;
import javax.persistence.Entity;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentocomCartao extends Pagamento {

  private static final long serialVersionUID = 1L;

  private Integer numeroDeParcelas;

  public PagamentocomCartao(){}

  public PagamentocomCartao(Integer id,
      EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
    super(id, estado, pedido);
    this.numeroDeParcelas = numeroDeParcelas;
  }

  public Integer getNumeroDeParcelas() {
    return numeroDeParcelas;
  }

  public PagamentocomCartao setNumeroDeParcelas(Integer numeroDeParcelas) {
    this.numeroDeParcelas = numeroDeParcelas;
    return this;
  }

}
