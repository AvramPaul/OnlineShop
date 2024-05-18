package com.magazin.RepositoryContCurent;

import com.magazin.EntityContCurent.ContCurent;
import com.magazin.entityCont.Cont;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContCurentRepo extends JpaRepository<ContCurent, Integer> {

}
