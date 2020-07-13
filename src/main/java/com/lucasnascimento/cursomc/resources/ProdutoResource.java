package com.lucasnascimento.cursomc.resources;

import com.lucasnascimento.cursomc.DTO.ProdutoDTO;
import com.lucasnascimento.cursomc.domain.Produto;
import com.lucasnascimento.cursomc.resources.Utils.Url;
import com.lucasnascimento.cursomc.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value ="/produtos")
public class ProdutoResource {

    @Autowired
    ProdutoService produtoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Integer id) {
        Produto produto = produtoService.find(id);

        return ResponseEntity.ok().body(produto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage",defaultValue = "4") Integer linesPerPage,
            @RequestParam(value = "orderBy",defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction",defaultValue = "ASC") String direction
    ) {
        String nameDecoded = Url.decodeParam(nome);
        List<Integer> categoriesIds = Url.decodeIntList(categorias);
        Page<Produto> produtos = produtoService.search(page, linesPerPage, orderBy, direction, nameDecoded, categoriesIds);
        Page<ProdutoDTO> produtoDTO = produtos.map(produto -> new ProdutoDTO(produto));

        return ResponseEntity.ok().body(produtoDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Produto> insert(@RequestBody Produto produto) {
        produtoService.insert(produto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


}
