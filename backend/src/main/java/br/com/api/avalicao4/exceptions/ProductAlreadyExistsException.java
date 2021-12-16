package br.com.api.avalicao4.exceptions;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductAlreadyExistsException extends ServiceException {

    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}