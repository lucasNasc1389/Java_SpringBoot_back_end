package com.lucasnascimento.cursomc.resources;

import com.lucasnascimento.cursomc.domain.Estado;
import com.lucasnascimento.cursomc.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

  @Autowired
  EstadoService estadoService;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Estado> find(@PathVariable Integer id) {
    Estado obj = estadoService.buscarEstado(id);

    return ResponseEntity.ok().body(obj);
  }

}
