package com.lucasnascimento.cursomc.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoCliente {

  PESSOAFISICA(1, "Pessoa Física"),
  PESSOAJURIDICA(2, "Pessoa Jurídica");

  private int codigo;
  private String descricao;

  private TipoCliente(int codigo, String descricao) {
    this.codigo = codigo;
    this.descricao = descricao;
  }

  public int getCodigo() {
    return codigo;
  }

  public String getDescricao() {
    return descricao;
  }

  @JsonValue
   public static TipoCliente toEnum(Integer cod) {

    if(cod == null) {
      return null;
    }

    for (TipoCliente x : TipoCliente.values()) {
      if(cod.equals(x.getCodigo())) {
        return x;
      }
    }

    throw new IllegalArgumentException("Id inválido" + cod);

   }
}
