package com.magazin.repositoryProdus;

import org.springframework.data.jpa.repository.JpaRepository;
import com.magazin.entityProdus.Produs;

public interface ProdusRepository extends JpaRepository<Produs, Integer>{
}
