package org.example.dao;

import org.example.dto.Produit;
import org.example.entites.ProduitsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<ProduitsEntity,Integer> {
    Optional<ProduitsEntity> findProduitEntitiesByIdEquals(int prd_num);
}
