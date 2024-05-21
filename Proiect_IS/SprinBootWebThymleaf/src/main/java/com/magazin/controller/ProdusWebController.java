package com.magazin.controller;

import com.magazin.entityCont.ContLogat;
import com.magazin.entityProdus.Produs;
import com.magazin.repositoryCont.ContLogatRepository;
import com.magazin.repositoryProdus.ProdusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProdusWebController {

    @Autowired
    ProdusRepository repository;

    @Autowired
    ContLogatRepository contLogatRepository;

    @GetMapping("/produse")
    public String getProduse(Model model) {
        List<ContLogat> contLogatList = contLogatRepository.findAll();
        boolean isVanzator = false;
        boolean isCumparator = false;
        boolean isAdmin = false;
        if (!contLogatList.isEmpty()) {
            ContLogat contLogat = contLogatList.get(0);  // Presupunem că există doar un singur utilizator logat
            isVanzator = "vanzator".equals(contLogat.getRole());
            isCumparator = "cumparator".equals(contLogat.getRole());
            isAdmin = "admin".equals(contLogat.getRole());
        }
        model.addAttribute("isVanzator", isVanzator);
        model.addAttribute("isCumparator", isCumparator);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("produse", repository.findAll());
        return "listaProduse";
    }
    @GetMapping("/adaugare_produse")
    public String vindeProdus(Model model) {
        return "vindeProdus";
    }
    @GetMapping("/adaugare_produs")
    public String adaugareProdus(@RequestParam String denumire, @RequestParam float pret, @RequestParam String descriere) {
        Produs produs = new Produs();
        produs.setDenumire(denumire);
        produs.setPret(pret);
        produs.setDescriere(descriere);
        List<ContLogat> contlogat = contLogatRepository.findAll();
        ContLogat contLogat = contlogat.stream().findFirst().get();
        produs.setVanzator(contLogat.getNume());
        repository.save(produs);

        return "redirect:/produse";
    }
}
