package com.lucasnascimento.cursomc.service;

import com.lucasnascimento.cursomc.domain.Categoria;
import com.lucasnascimento.cursomc.domain.Produto;
import com.lucasnascimento.cursomc.repository.CategoriaRepository;
import com.lucasnascimento.cursomc.repository.ProdutoRepository;
import com.lucasnascimento.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    public Produto find(Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        return produto.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()
        ));
    }

    public Page<Produto> search(Integer page, Integer linesPerPage, String orderBy, String direction, String nome, List<Integer> categorieaIds) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(categorieaIds);

        return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }
}
