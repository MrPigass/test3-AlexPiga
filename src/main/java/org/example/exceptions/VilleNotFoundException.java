package org.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class VilleNotFoundException extends RuntimeException {
    public VilleNotFoundException(String nomVille) {
        super("ville "+nomVille+" pas trouvée");
    }
}
