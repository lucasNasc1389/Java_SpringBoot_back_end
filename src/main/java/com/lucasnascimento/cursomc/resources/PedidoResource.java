package com.lucasnascimento.cursomc.resources;

import com.lucasnascimento.cursomc.domain.Pedido;
import com.lucasnascimento.cursomc.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

  @Autowired
  PedidoService pedidoService;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Pedido> find( @PathVariable Integer id ) {
    Pedido pedido = pedidoService.buscarPedido(id);

    return ResponseEntity.ok().body(pedido);
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Pedido> insert(@Valid @RequestBody Pedido obj) {
    Pedido pedido = pedidoService.insert(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(pedido.getId()).toUri();

    return ResponseEntity.created(uri).build();

  }


}
