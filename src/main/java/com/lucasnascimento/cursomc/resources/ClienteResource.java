package com.lucasnascimento.cursomc.resources;

import com.lucasnascimento.cursomc.domain.Cliente;
import com.lucasnascimento.cursomc.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

  @Autowired
  ClienteService clienteService;

  @RequestMapping(value = "/{idCliente}", method = RequestMethod.GET)
  public ResponseEntity<Cliente> buscarCliente(@PathVariable Integer idCliente) {
    Cliente cliente =  clienteService.buscarCliente(idCliente);

    return ResponseEntity.ok().body(cliente);
  }




}
