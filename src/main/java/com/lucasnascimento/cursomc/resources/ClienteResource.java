package com.lucasnascimento.cursomc.resources;

import com.lucasnascimento.cursomc.DTO.ClienteDTO;
import com.lucasnascimento.cursomc.DTO.ClienteNewDTO;
import com.lucasnascimento.cursomc.domain.Cliente;
import com.lucasnascimento.cursomc.service.ClienteService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    ClienteService clienteService;

    @RequestMapping(value = "/{idCliente}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer idCliente) {
        Cliente cliente = clienteService.find(idCliente);

        return ResponseEntity.ok().body(cliente);
    }

    @RequestMapping(value = "/{idCliente}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer idCliente) {
        Cliente cliente = clienteService.fromDTO(clienteDTO);
        clienteDTO.setId(idCliente);
        clienteService.update(cliente);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{idCliente}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer idCliente) {
        clienteService.delete(idCliente);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> listaClientes = clienteService.findAll();
        List<ClienteDTO> listaClientesDTO = listaClientes.stream()
                .map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaClientesDTO);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "linesPerPage", defaultValue = "2") Integer linesPerPage,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "orderBy", defaultValue = "nome") String nome
    ) {

        Page<Cliente> clientes = clienteService.findPage(page, linesPerPage, direction, nome);
        Page<ClienteDTO> clienteDTO = clientes.map(cliente -> new ClienteDTO(cliente));

        return ResponseEntity.ok().body(clienteDTO);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clienteNewDTO) {

        Cliente cliente = clienteService.fromClienteDTO(clienteNewDTO);
        Cliente obj = clienteService.insert(cliente);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();


    }


}
