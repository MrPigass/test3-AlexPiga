package org.example.dao;

import org.example.entites.ProduitsEntity;
import org.example.entites.VillesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VilleRepository extends JpaRepository<ProduitsEntity,Integer> {
    Optional<VillesEntity> findVilleEntitiesByVilNumEquals(int vil_num);
}
