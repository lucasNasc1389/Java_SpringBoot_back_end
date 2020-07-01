package com.lucasnascimento.cursomc.service;

import com.lucasnascimento.cursomc.DTO.ClienteDTO;
import com.lucasnascimento.cursomc.DTO.ClienteNewDTO;
import com.lucasnascimento.cursomc.domain.Cidade;
import com.lucasnascimento.cursomc.domain.Cliente;
import com.lucasnascimento.cursomc.domain.Endereco;
import com.lucasnascimento.cursomc.domain.enums.TipoCliente;
import com.lucasnascimento.cursomc.repository.ClienteRepository;
import com.lucasnascimento.cursomc.repository.EnderecoRepository;
import com.lucasnascimento.cursomc.service.exceptions.DataIntegrityException;
import com.lucasnascimento.cursomc.service.exceptions.ObjectNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    public Cliente find(Integer idCliente) {
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);

        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "Cliente não foi encontrado! Id: " + idCliente + ", Tipo: " + Cliente.class.getName()
        ));
    }

    public Cliente update(Cliente cliente) {
        Cliente clienteUpdate = find(cliente.getId());
        updateData(clienteUpdate, cliente);
        return clienteRepository.save(clienteUpdate);
    }

    private void updateData(Cliente clienteUpdate, Cliente cliente) {
        clienteUpdate.setNome(cliente.getNome());
        clienteUpdate.setEmail(cliente.getEmail());
    }

    public Cliente fromDTO(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
    }

    public void delete(Integer idCliente) {
        find(idCliente);
        try {
            clienteRepository.deleteById(idCliente);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível deletar um cliente que possui pedidos!");
        }

    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromClienteDTO(ClienteNewDTO clienteNewDTO) {
        Cliente cliente = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(), clienteNewDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteNewDTO.getTipo()));
        Cidade cid = new Cidade(clienteNewDTO.getIdCidade(), null, null);
        Endereco end = new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(), clienteNewDTO.getComplemento(), clienteNewDTO.getBairro(), clienteNewDTO.getCep(), cliente, cid);

        cliente.getEnderecos().add(end);
        cliente.getTelefones().add(clienteNewDTO.getTelefone1());

        if (clienteNewDTO.getTelefone2() != null) {
            cliente.getTelefones().add(clienteNewDTO.getTelefone2());
        }
        if (clienteNewDTO.getTelefone3() != null) {
            cliente.getTelefones().add(clienteNewDTO.getTelefone3());
        }

        return cliente;
    }

    @Transactional
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());

        return cliente;
    }
}
