package com.lucasnascimento.cursomc.resources.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String message;

    public FieldMessage() {

    }

    public FieldMessage(String fieldName, String message) {
        this.message = message;
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public FieldMessage setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
