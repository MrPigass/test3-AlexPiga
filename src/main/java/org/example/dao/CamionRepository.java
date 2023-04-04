package org.example.dao;

import org.example.entites.CamionsEntity;
import org.example.entites.ProduitsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CamionRepository extends JpaRepository<ProduitsEntity,Integer> {
    Optional<CamionsEntity> findCamionEntitiesByIdEquals(int cmn_num);


}
