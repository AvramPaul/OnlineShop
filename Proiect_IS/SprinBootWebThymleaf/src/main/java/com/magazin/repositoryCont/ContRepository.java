package com.magazin.repositoryCont;

import org.springframework.data.jpa.repository.JpaRepository;
import com.magazin.entityCont.Cont;

public interface ContRepository extends JpaRepository<Cont, Integer>{
}
