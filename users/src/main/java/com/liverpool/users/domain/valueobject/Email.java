package com.liverpool.users.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Email {

    private final String value;

    private Email(String value) {
        this.value = value;
    }

    private Email() {
        this.value = null;
    }

    public static Email of(String value) {
        if(value == null || value.isBlank()){
            throw new IllegalArgumentException("Correo electronico obligatorio para registro");
        }
        if(!value.matches("^[\\w.+\\-]+@[\\w\\-]+\\.[a-z]{2,}$")){
            throw new IllegalArgumentException("Formato del correo electronico invalido: "+ value);
        }
        return new Email(value.toLowerCase().trim());
    }

}
