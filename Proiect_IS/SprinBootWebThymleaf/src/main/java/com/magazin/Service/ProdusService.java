package com.magazin.Service;

import com.magazin.entityProdus.Produs;
import com.magazin.repositoryProdus.ProdusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ProdusService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProdusRepository produsRepository;

    @Transactional
    public void addOffer(int produsId, float oferta) {
        Produs produs = produsRepository.findById(produsId)
                .orElseThrow(() -> new RuntimeException("Produs not found"));

        produs.getOferte().add(oferta);
        produsRepository.save(produs);
    }

    @Transactional
    public void acceptOffer(int produsId, float oferta) {
        Produs produs = produsRepository.findById(produsId)
                .orElseThrow(() -> new RuntimeException("Produs not found"));
        produs.setOfertaAcceptata(oferta);
        produs.setVanzator(null);

        produsRepository.save(produs);
    }




}
