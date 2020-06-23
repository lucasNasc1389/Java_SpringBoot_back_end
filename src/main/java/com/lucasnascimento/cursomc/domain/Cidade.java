package com.lucasnascimento.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cidade implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;

  @ManyToOne
  @JoinColumn(name = "estado_id")
  private Estado estado;


  public Cidade(){}

  public Cidade(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public Cidade setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Cidade setName(String name) {
    this.name = name;
    return this;
  }

  public Estado getEstado() {
    return estado;
  }

  public Cidade setEstado(Estado estado) {
    this.estado = estado;
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
    Cidade cidade = (Cidade) o;
    return id.equals(cidade.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
