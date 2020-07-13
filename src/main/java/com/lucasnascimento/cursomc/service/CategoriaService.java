package com.lucasnascimento.cursomc.service;

import com.lucasnascimento.cursomc.DTO.CategoriaDTO;
import com.lucasnascimento.cursomc.domain.Categoria;
import com.lucasnascimento.cursomc.repository.CategoriaRepository;
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
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()
        ));
    }

    @Transactional
    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return categoriaRepository.save(obj);
    }

    public Categoria update(Categoria obj) {
        Categoria categoriaUpdate = find(obj.getId());
        updateData(categoriaUpdate, obj);
        return categoriaRepository.save(obj);
    }

    private void updateData(Categoria categoriaUpdate, Categoria obj) {
        categoriaUpdate.setNome(obj.getNome());
    }

    public void delete(Integer id) {
        find(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível deletar uma categoria que possui produtos!");
        }

    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO objDTO) {
        return new Categoria(objDTO.getId(), objDTO.getNome());
    }

}
