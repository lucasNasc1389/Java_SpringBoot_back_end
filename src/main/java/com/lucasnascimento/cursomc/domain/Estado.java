package com.lucasnascimento.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class Estado implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;

  @JsonIgnore
  @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
  private List<Cidade> cidades = new ArrayList<>();

  public Estado(){}

  public Estado(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public Estado setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Estado setName(String name) {
    this.name = name;
    return this;
  }

  public List<Cidade> getCidades() {
    return cidades;
  }

  public Estado setCidades(List<Cidade> cidades) {
    this.cidades = cidades;
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
    Estado estado = (Estado) o;
    return Objects.equals(id, estado.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
