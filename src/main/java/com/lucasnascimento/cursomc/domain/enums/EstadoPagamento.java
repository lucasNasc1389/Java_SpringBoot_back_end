package com.lucasnascimento.cursomc.domain.enums;


public enum EstadoPagamento {

  QUITADO(1, "Quitado"),
  PENDENTE(2, "Em aberto"),
  CANCELADO(3, "Cancelado");


  private Integer codigo;
  private String descricao;

  private EstadoPagamento(Integer codigo, String descricao){
    this.codigo = codigo;
    this.descricao = descricao;
  }

  public Integer getCodigo() {
    return codigo;
  }

  public EstadoPagamento setCodigo(Integer codigo) {
    this.codigo = codigo;
    return this;
  }

  public String getDescricao() {
    return descricao;
  }

  public EstadoPagamento setDescricao(String descricao) {
    this.descricao = descricao;
    return this;
  }

  public static EstadoPagamento toEnum(Integer cod) {

    if(cod == null) {
      return null;
    }

    for(EstadoPagamento x : EstadoPagamento.values()) {
      if(cod.equals(x.getCodigo())){
        return x;
      }
    }

    throw new IllegalArgumentException("Código informado inválido: " + cod);

  }
}
