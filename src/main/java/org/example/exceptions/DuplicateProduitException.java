package org.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateProduitException extends RuntimeException {
    public DuplicateProduitException(String description) {
        super("Client "+description+" déjà existant");
    }
}
