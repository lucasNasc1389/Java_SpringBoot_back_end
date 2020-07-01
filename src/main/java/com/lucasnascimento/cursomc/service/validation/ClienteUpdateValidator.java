package com.lucasnascimento.cursomc.service.validation;

import com.lucasnascimento.cursomc.DTO.ClienteDTO;
import com.lucasnascimento.cursomc.DTO.ClienteNewDTO;
import com.lucasnascimento.cursomc.domain.Cliente;
import com.lucasnascimento.cursomc.domain.enums.TipoCliente;
import com.lucasnascimento.cursomc.repository.ClienteRepository;
import com.lucasnascimento.cursomc.resources.exceptions.FieldMessage;
import com.lucasnascimento.cursomc.service.validation.Utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.*;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    HttpServletRequest request;

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());

        if (aux != null && !aux.getId().equals(uriId)){
            list.add(new FieldMessage("email", "Email já cadastrado"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}