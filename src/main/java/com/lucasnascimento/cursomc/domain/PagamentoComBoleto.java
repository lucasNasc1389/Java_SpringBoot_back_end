package com.lucasnascimento.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasnascimento.cursomc.domain.enums.EstadoPagamento;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PagamentoComBoleto extends Pagamento{

  private static final long serialVersionUID = 1L;

  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern="dd/MM/yyyy HH:mm")
  private Date dataDeVencimento;

  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern="dd/MM/yyyy HH:mm")
  private Date dataDePagamento;

  public PagamentoComBoleto(){}

  public PagamentoComBoleto(Integer id,
      EstadoPagamento estado, Pedido pedido, Date dataDeVencimento, Date dataDePagamento) {
    super(id, estado, pedido);
    this.dataDeVencimento = dataDeVencimento;
    this.dataDePagamento = dataDePagamento;
  }

  public Date getDataDeVencimento() {
    return dataDeVencimento;
  }

  public PagamentoComBoleto setDataDeVencimento(Date dataDeVencimento) {
    this.dataDeVencimento = dataDeVencimento;
    return this;
  }

  public Date getDataDePagamento() {
    return dataDePagamento;
  }

  public PagamentoComBoleto setDataDePagamento(Date dataDePagamento) {
    this.dataDePagamento = dataDePagamento;
    return this;
  }
}
