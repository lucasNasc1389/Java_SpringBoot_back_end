package com.lucasnascimento.cursomc.service;

import com.lucasnascimento.cursomc.DTO.ClienteDTO;
import com.lucasnascimento.cursomc.domain.Cliente;
import com.lucasnascimento.cursomc.repository.ClienteRepository;
import com.lucasnascimento.cursomc.service.exceptions.DataIntegrityException;
import com.lucasnascimento.cursomc.service.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

  @Autowired
  ClienteRepository clienteRepository;

  public Cliente find(Integer idCliente) {
    Optional<Cliente> cliente = clienteRepository.findById(idCliente);

    return cliente.orElseThrow(() -> new ObjectNotFoundException(
        "Cliente não foi encontrado! Id: " + idCliente + ", Tipo: "+ Cliente.class.getName()
    ));
  }

  public Cliente update(Cliente cliente) {
    find(cliente.getId());
    return clienteRepository.save(cliente);
  }

 public Cliente fromDTO(ClienteDTO clienteDTO) {
    return new Cliente(
            clienteDTO.getId(),
            clienteDTO.getNome(),
            clienteDTO.getEmail(),
            clienteDTO.getCpfOuCnpj(),
            clienteDTO.getTipo());
 }

    public void delete(Integer idCliente) {
      find(idCliente);
      try {
          clienteRepository.deleteById(idCliente);
      } catch (DataIntegrityViolationException e) {
          throw  new DataIntegrityException("Não é possível deletar um cliente que possui pedidos!");
      }

    }

    public List<Cliente> findAll() {
      return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }
}
