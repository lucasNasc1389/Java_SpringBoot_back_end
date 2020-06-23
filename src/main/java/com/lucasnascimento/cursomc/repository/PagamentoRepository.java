package com.lucasnascimento.cursomc.repository;

import com.lucasnascimento.cursomc.domain.Categoria;
import com.lucasnascimento.cursomc.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
