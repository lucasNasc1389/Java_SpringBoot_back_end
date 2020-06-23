package com.lucasnascimento.cursomc.service;

import com.lucasnascimento.cursomc.domain.Cliente;
import com.lucasnascimento.cursomc.repository.ClienteRepository;
import com.lucasnascimento.cursomc.service.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

  @Autowired
  ClienteRepository clienteRepository;

  public Cliente buscarCliente(Integer idCliente) {
    Optional<Cliente> cliente = clienteRepository.findById(idCliente);

    return cliente.orElseThrow(() -> new ObjectNotFoundException(
        "Cliente não foi encontrado! Id: " + idCliente + ", Tipo: "+ Cliente.class.getName()
    ));
  }
}
