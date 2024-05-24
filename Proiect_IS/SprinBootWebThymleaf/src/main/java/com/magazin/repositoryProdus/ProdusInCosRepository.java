package com.magazin.repositoryProdus;

import com.magazin.entityProdus.ProdusInCos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdusInCosRepository extends JpaRepository<ProdusInCos, Integer> {
    List<ProdusInCos> findAllByIdCumparator(int id);
}
