package com.magazin.repositoryProdus;

import org.springframework.data.jpa.repository.JpaRepository;
import com.magazin.entityProdus.Produs;

import java.util.List;

public interface ProdusRepository extends JpaRepository<Produs, Integer>{
    List<Produs> findAllByVanzator(String vanzator);
}
