package org.example.controller;
import org.example.dto.Produit;

import org.example.entites.ProduitsEntity;
import org.example.dao.ProduitRepository;
import org.example.entites.VillesEntity;
import org.example.dao.VilleRepository;
import org.example.exceptions.DuplicateProduitException;
import org.example.exceptions.ProduitBadRequestException;
import org.example.exceptions.ProduitNotFoundException;
import org.example.exceptions.VilleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class ProduitController
{
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private VilleRepository villeRepository;

    @GetMapping(value="/produits/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable("id") int id) throws ProduitNotFoundException {
        Optional<ProduitsEntity> opt = produitRepository.findProduitEntitiesByIdEquals(id);
        if (opt.isEmpty()) {
            throw new ProduitNotFoundException(id);
        }
        ProduitsEntity produitEntity = opt.get();

        // Récupération du nom de la ville à partir de la table Ville
        Optional<VillesEntity> villeOpt = villeRepository.findVilleEntitiesByVilNumEquals(produitEntity.getVilNum());
        String nomVille = villeOpt.isPresent() ? villeOpt.get().getNom() : "Ville inconnue";

        Produit produit = new Produit(produitEntity.getPrdNum(),
                produitEntity.getDescription(), produitEntity.getPoids(), nomVille);

        return new ResponseEntity<Produit>(produit, HttpStatus.OK );
    }

    @PostMapping(value="/produits")
    public ResponseEntity<Produit> insertProduit(@RequestBody Produit produit)  {
        Optional<ProduitsEntity> opt = produitRepository.findProduitEntitiesByIdEquals(produit.getId());
        if (opt.isPresent()) {
            throw new DuplicateProduitException(produit.getNom());
        }
        ProduitsEntity produitEntity = new ProduitsEntity();
        produitEntity.setPrdNum(produit.getId());
        produitEntity.setDescription(produit.getDescription());
        produitEntity.setPoids(produit.getPoids());
        // Recherche de la ville correspondante dans la table Ville
        Optional<VillesEntity> villeOpt = villeRepository.findVilleEntitiesByVilNumEquals(produitEntity.getVilNum());
        if (villeOpt.isPresent()) {
            produitEntity.setVilNum(villeOpt.get().getVilNum());
        } else {
            throw new VilleNotFoundException(produit.getVille());
        }
        if (produit.getDescription().length() > 20) {
            throw new ProduitBadRequestException();
        }
        else if (produit.getPoids() < 1 || produit.getPoids() > 1000) {
            throw new ProduitBadRequestException();
            /*pas de description ou pas de poids */
        } else if (produit.getDescription().length() == 0 || produit.getPoids() == 0) {
            throw new ProduitBadRequestException();
        }

        ProduitsEntity produitEntityNew = produitRepository.save(produitEntity);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produitEntityNew.getPrdNum())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
