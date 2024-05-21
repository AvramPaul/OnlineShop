package com.magazin.repositoryCont;

import com.magazin.entityCont.ContLogat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContLogatRepository extends JpaRepository<ContLogat, Integer> {

}
