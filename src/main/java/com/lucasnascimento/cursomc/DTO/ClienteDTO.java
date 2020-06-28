package com.lucasnascimento.cursomc.DTO;

import com.lucasnascimento.cursomc.domain.Cliente;
import com.lucasnascimento.cursomc.domain.Endereco;
import com.lucasnascimento.cursomc.domain.enums.TipoCliente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;

    @NotEmpty(message = "O preenchimento do campo é obrigatório.")
    @Length(min=3, max = 80, message = "O nome deve conter entre 3 e 80 caracteres")
    private String nome;

    @NotEmpty(message = "O preenchimento do campo é obrigatório.")
    @Email(message = "Email inválido")
    private String email;
    private String cpfOuCnpj;
    private Integer tipo;
    private List<Endereco> enderecos;
    private Set<String> telefones;



    public ClienteDTO() {}

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }

    public ClienteDTO(Integer id, String nome, String email, List<Endereco> enderecos, Set<String> telefones, TipoCliente tipo, String cpfOuCnpj) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.enderecos = enderecos;
        this.telefones = telefones;
        this.tipo = tipo.getCodigo();
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getId() {
        return id;
    }

    public ClienteDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return this.nome;
    }

    public ClienteDTO setNome( String nome ) {
        this.nome = nome;
        return this;
    }

    public String getEmail() {
        return this.email;
    }

    public ClienteDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public ClienteDTO setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
        return this;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public ClienteDTO setTelefones(Set<String> telefones) {
        this.telefones = telefones;
        return this;
    }


    public TipoCliente getTipo() {
        return TipoCliente.toEnum(tipo);
    }

    public ClienteDTO setTipo(TipoCliente tipo) {
        this.tipo = tipo.getCodigo();
        return this;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public ClienteDTO setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
        return this;
    }
}
