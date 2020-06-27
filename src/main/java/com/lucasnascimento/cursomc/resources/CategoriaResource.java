package com.lucasnascimento.cursomc.resources;

import com.lucasnascimento.cursomc.DTO.CategoriaDTO;
import com.lucasnascimento.cursomc.domain.Categoria;
import com.lucasnascimento.cursomc.service.CategoriaService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

  @Autowired
  private CategoriaService service;

  @RequestMapping(value="/{id}", method = RequestMethod.GET)
  public ResponseEntity<Categoria> find(@PathVariable Integer id) {

    Categoria obj = service.find(id);

    return ResponseEntity.ok().body(obj);
  }


  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO) {
    Categoria obj = service.fromDTO(objDTO);
    obj = service.insert(obj);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(obj.getId()).toUri();

    return ResponseEntity.created(uri).build();
  }

  @RequestMapping(value="/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id) {
    Categoria obj = service.fromDTO(objDTO);
    obj.setId(id);
    obj = service.update(obj);

    return ResponseEntity.noContent().build();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> delete(@PathVariable Integer id){
    service.delete(id);

    return ResponseEntity.noContent().build();
  }

  @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll(){
      List<Categoria> listCategoria = service.findAll();
      List<CategoriaDTO> listCategoriaDTO = listCategoria.stream()
              .map(categoria -> new CategoriaDTO(categoria)).collect(Collectors.toList());
      return ResponseEntity.ok().body(listCategoriaDTO);
  }

  @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(
          @RequestParam(value = "page", defaultValue = "0") Integer page,
          @RequestParam(value = "linesPerPage", defaultValue = "2") Integer linesPerPage,
          @RequestParam(value = "direction", defaultValue = "ASC") String direction,
          @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
  ) {
    Page<Categoria> listaCategoria = service.findPage(page, linesPerPage, direction, orderBy);
    Page<CategoriaDTO> listaCategoriaDTO = listaCategoria.map(categoria -> new CategoriaDTO(categoria));
    return ResponseEntity.ok().body(listaCategoriaDTO);
  }
}
