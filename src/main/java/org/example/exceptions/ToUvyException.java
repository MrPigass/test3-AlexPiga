package org.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ToUvyException extends RuntimeException {
    public ToUvyException(int cmnNum) {
        super("Produit trop lourd pour le camion "+cmnNum);
    }
}
