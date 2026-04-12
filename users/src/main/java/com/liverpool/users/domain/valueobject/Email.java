package com.liverpool.users.domain.valueobject;

import com.liverpool.users.domain.util.DomainValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.PersistenceCreator;

@Getter
@EqualsAndHashCode
public class Email {

    private final String value;

    @PersistenceCreator
    private Email(String value) {
        this.value = value;
    }

    private Email() {
        this.value = null;
    }

    public static Email of(String value) {
        DomainValidator.validateNotBlank(value,"Correo electronico obligatorio para registro");
        DomainValidator.validatePattern(value,"^[\\w.+\\-]+@[\\w\\-]+\\.[a-z]{2,}$","Formato del correo electronico invalido: "+ value);
        return new Email(value.toLowerCase().trim());
    }

}
