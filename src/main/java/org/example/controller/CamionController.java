package org.example.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.example.dao.CamionRepository;
import org.example.dao.ProduitRepository;
import org.example.dto.Camion;
import org.example.dto.CamionNew;
import org.example.dto.Produit;
import org.example.entites.CamionsEntity;
import org.example.entites.ProduitsEntity;
import org.example.exceptions.CamionNotFoundException;
import org.example.exceptions.ProduitNotFoundException;
import org.example.exceptions.ToUvyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class CamionController {
    @Autowired
    private CamionRepository camionRepository;
    @Autowired
    private ProduitRepository produitRepository;

    @PostMapping(value="/camions/{cmn_num}")
    public ResponseEntity<Camion> insertCamion(@PathVariable("cmn_num") int cmn_num,@RequestParam("produit") int produit)  {
        Optional<CamionsEntity> opt = camionRepository.findCamionEntitiesByIdEquals(cmn_num);
        Optional<ProduitsEntity> opt2 = produitRepository.findProduitEntitiesByIdEquals(produit);
        if (opt.isEmpty()) {
            throw new CamionNotFoundException(cmn_num);
        }
        if (opt2.isEmpty()) {
            throw new ProduitNotFoundException(produit);
        }
        ProduitsEntity produitEntity = new ProduitsEntity();
        produitEntity.setPrdNum(opt2.get().getPrdNum());
        produitEntity.setDescription(opt2.get().getDescription());
        produitEntity.setPoids(opt2.get().getPoids());
        produitEntity.setVilNum(opt2.get().getVilNum());
        produitEntity.setCmnNum(opt2.get().getCmnNum());
        if(opt2.get().getCmnNum() != null){
            throw new ProduitNotFoundException(cmn_num);
        } else if (opt.get().getChargeMax()<opt2.get().getPoids()+opt.get().getCharge()) {
            throw new ToUvyException(cmn_num);
        }
        CamionsEntity camionsEntity = new CamionsEntity();
        camionsEntity.setCmnNum(cmn_num);
        camionsEntity.setCharge(opt.get().getCharge()+opt2.get().getPoids());
        produitEntity.setCmnNum(camionsEntity.getCmnNum());
        CamionsEntity camionsEntityNew = camionRepository.save(camionsEntity);
        produitRepository.save(produitEntity);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{cmn_num}")
                .buildAndExpand(camionsEntityNew.getCmnNum())
                .toUri();
        return ResponseEntity.created(uri).build();

    }

}
