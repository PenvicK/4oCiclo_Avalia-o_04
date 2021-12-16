package br.com.api.avalicao4.exceptions;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends ServiceException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}