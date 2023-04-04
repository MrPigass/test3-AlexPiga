package org.example.controller;
import org.example.dto.Produit;

import org.example.entites.ProduitsEntity;
import org.example.dao.ProduitRepository;
import org.example.exceptions.ProduitNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProduitController
{
    @GetMapping(value="/produits/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable("id") int id) throws ProduitNotFoundException {
        Optional<ProduitsEntity> opt = ProduitRepository.findProduitEntitiesByIdEquals(id);
        if (opt.isEmpty()) {
            throw new ProduitNotFoundException(id);
        }
        ProduitsEntity produitEntity = opt.get();
        Produit produit = new Produit(produitEntity.getPrdNum()),
                 produitEntity.getNom(), produitEntity.getPrix());
        return new ResponseEntity<Produit>(produit, HttpStatus.OK );
    }
}
