package com.magazin.repositoryCont;

import com.magazin.entityCont.Cont;
import com.magazin.entityCont.ContAnulat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContAnulatRepository extends JpaRepository<ContAnulat, Integer> {
}
