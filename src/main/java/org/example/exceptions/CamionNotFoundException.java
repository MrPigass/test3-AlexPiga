package org.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CamionNotFoundException extends RuntimeException {
    public CamionNotFoundException(int cmnNum) {
        super("Produit "+cmnNum+" pas trouvé ou déjà chargé");
    }
}
