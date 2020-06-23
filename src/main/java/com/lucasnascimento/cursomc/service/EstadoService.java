package com.lucasnascimento.cursomc.service;

import com.lucasnascimento.cursomc.domain.Estado;
import com.lucasnascimento.cursomc.repository.EstadoRepository;
import com.lucasnascimento.cursomc.service.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

  @Autowired
  private EstadoRepository estadoRepository;

  public Estado buscarEstado(Integer id) {
    Optional<Estado> obj = estadoRepository.findById(id);

    return obj.orElseThrow(() -> new ObjectNotFoundException(
        "Objeto não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()
    ));
  }

}
