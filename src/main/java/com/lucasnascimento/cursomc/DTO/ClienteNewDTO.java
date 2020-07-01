package com.lucasnascimento.cursomc.DTO;

import com.lucasnascimento.cursomc.domain.Endereco;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ClienteNewDTO  implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "O preenchimento do campo é obrigatório.")
    @Length(min=3, max = 80, message = "O nome deve conter entre 3 e 80 caracteres")
    private String nome;

    @NotEmpty(message = "O preenchimento do campo é obrigatório.")
    @Email(message = "Email inválido")
    private String email;

    @NotEmpty(message = "O preenchimento do campo é obrigatório.")
    private String cpfOuCnpj;
    private Integer tipo;

    @NotEmpty(message = "O preenchimento do campo é obrigatório.")
    private String logradouro;

    @NotEmpty(message = "O preenchimento do campo é obrigatório.")
    private String numero;

    private String complemento;
    private String bairro;

    @NotEmpty(message = "O preenchimento do campo é obrigatório.")
    private String cep;

    @NotEmpty(message = "O preenchimento do campo é obrigatório.")
    private String telefone1;

    private String telefone2;
    private String telefone3;

    private Integer idCidade;

    public ClienteNewDTO() {

    }

    public String getNome() {
        return nome;
    }

    public ClienteNewDTO setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ClienteNewDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public ClienteNewDTO setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
        return this;
    }

    public Integer getTipo() {
        return tipo;
    }

    public ClienteNewDTO setTipo(Integer tipo) {
        this.tipo = tipo;
        return this;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public ClienteNewDTO setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public String getNumero() {
        return numero;
    }

    public ClienteNewDTO setNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public String getComplemento() {
        return complemento;
    }

    public ClienteNewDTO setComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public ClienteNewDTO setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getCep() {
        return cep;
    }

    public ClienteNewDTO setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public ClienteNewDTO setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
        return this;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public ClienteNewDTO setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
        return this;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public ClienteNewDTO setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
        return this;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public ClienteNewDTO setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
        return this;
    }
}
