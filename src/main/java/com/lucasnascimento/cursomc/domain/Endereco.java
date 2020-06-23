package com.lucasnascimento.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Endereco implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String logradouro;
  private String numero;
  private String complemento;
  private String bairro;
  private String cep;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;


  @ManyToOne
  @JoinColumn(name= "cidade_id")
  private Cidade cidade;

  public Endereco() {}


  public Endereco(Integer id, String logradouro, String numero, String complemento,
      String bairro, String cep, Cliente cliente, Cidade cidade) {
    this.id = id;
    this.logradouro = logradouro;
    this.numero = numero;
    this.complemento = complemento;
    this.bairro = bairro;
    this.cep = cep;
    this.cliente = cliente;
    this.cidade = cidade;
  }

  public Integer getId() {
    return id;
  }

  public Endereco setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getLogradouro() {
    return logradouro;
  }

  public Endereco setLogradouro(String logradouro) {
    this.logradouro = logradouro;
    return this;
  }

  public String getNumero() {
    return numero;
  }

  public Endereco setNumero(String numero) {
    this.numero = numero;
    return this;
  }

  public String getComplemento() {
    return complemento;
  }

  public Endereco setComplemento(String complemento) {
    this.complemento = complemento;
    return this;
  }

  public String getBairro() {
    return bairro;
  }

  public Endereco setBairro(String bairro) {
    this.bairro = bairro;
    return this;
  }

  public String getCep() {
    return cep;
  }

  public Endereco setCep(String cep) {
    this.cep = cep;
    return this;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public Endereco setCliente(Cliente cliente) {
    this.cliente = cliente;
    return this;
  }

  public Cidade getCidade() {
    return cidade;
  }

  public Endereco setCidade(Cidade cidade) {
    this.cidade = cidade;
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
    Endereco endereco = (Endereco) o;
    return id.equals(endereco.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
