package com.lucasnascimento.cursomc.resources;

import com.lucasnascimento.cursomc.domain.Cidade;
import com.lucasnascimento.cursomc.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cidade")
public class CidadeResource {

  @Autowired
  CidadeService cidadeService;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Cidade> find(@PathVariable Integer id) {
    Cidade obj = cidadeService.buscarCidade(id);

    return ResponseEntity.ok().body(obj);
  }

}
