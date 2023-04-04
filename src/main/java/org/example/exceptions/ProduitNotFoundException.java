package org.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProduitNotFoundException extends RuntimeException {
    public ProduitNotFoundException(int prd_num) {
        super("Produit "+prd_num+" pas trouvé");
    }
}
