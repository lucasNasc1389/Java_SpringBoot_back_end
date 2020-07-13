package com.lucasnascimento.cursomc.service;

import com.lucasnascimento.cursomc.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {
    public void preencherPagamentoNomBoleto(PagamentoComBoleto pagto, Date dataPedido) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 7);
        pagto.setDataDeVencimento(cal.getTime());
    }
}
