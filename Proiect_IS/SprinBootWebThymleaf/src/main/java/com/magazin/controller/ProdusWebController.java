package com.magazin.controller;

import com.magazin.entityProdus.Produs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.magazin.repositoryProdus.ProdusRepository;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProdusWebController {

    @Autowired
    ProdusRepository repository;

    @GetMapping("/produse")
    public String getListaProduse(Model model)
    {
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

        repository.save(produs);

        return "redirect:/produse";
    }


}
