package com.lucasnascimento.cursomc.DTO;

import com.lucasnascimento.cursomc.domain.enums.TipoCliente;

public class ClienteDTO {

    private String nome;
    private String email;
    private String cpfOuCNPJ;
    private Integer tipo;

    public ClienteDTO() {}

    public ClienteDTO(String nome, String email, String cpfOuCNPJ, TipoCliente tipo) {
        this.nome = nome;
        this.email = email;
        this.cpfOuCNPJ = cpfOuCNPJ;
        this.tipo = tipo.getCodigo();
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

    public String getCpfOuCNPJ() {
        return cpfOuCNPJ;
    }

    public ClienteDTO setCpfOuCNPJ(String cpfOuCNPJ) {
        this.cpfOuCNPJ = cpfOuCNPJ;
        return this;
    }

    public TipoCliente getTipo() {
        return TipoCliente.toEnum(tipo);
    }

    public ClienteDTO setTipo( TipoCliente tipo) {
        this.tipo = tipo.getCodigo();
        return this;
    }
}
