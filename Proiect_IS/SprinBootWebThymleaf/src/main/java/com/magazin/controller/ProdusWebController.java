package com.magazin.controller;

import com.magazin.Service.ProdusService;
import com.magazin.entityCont.ContLogat;
import com.magazin.entityProdus.Produs;
import com.magazin.entityProdus.ProdusInCos;
import com.magazin.repositoryCont.ContLogatRepository;
import com.magazin.repositoryProdus.ProdusInCosRepository;
import com.magazin.repositoryProdus.ProdusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Optional;

@Controller
public class ProdusWebController {



    @Autowired
    ProdusRepository repository;
    @Autowired
    ProdusInCosRepository produsInCosRepository;
    @Autowired
    ContLogatRepository contLogatRepository;

    @GetMapping("/produse")
    public String getProduse(Model model) {
        List<ContLogat> contLogatList = contLogatRepository.findAll();
        boolean isVanzator = false;
        boolean isCumparator = false;
        boolean isAdmin = false;
        boolean isLogat = false;
        String numeUser = "";
        if (!contLogatList.isEmpty()) {
            ContLogat contLogat = contLogatList.get(0);  // Presupunem că există doar un singur utilizator logat
            isLogat = true;
            isVanzator = "vanzator".equals(contLogat.getRole());
            isCumparator = "cumparator".equals(contLogat.getRole());
            isAdmin = "admin".equals(contLogat.getRole());
            numeUser = contLogat.getNume();
        }
        model.addAttribute("isVanzator", isVanzator);
        model.addAttribute("isCumparator", isCumparator);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isLogat", isLogat);
        model.addAttribute("nume", numeUser);
        model.addAttribute("produse", repository.findAll());
        return "listaProduse";
    }
    @GetMapping("/adaugare_produse")
    public String vindeProdus(Model model) {
        return "vindeProdus";
    }

    @GetMapping("/produse_user")
    public String produseUser(Model model){
        List<ContLogat> contLogatList = contLogatRepository.findAll();
        String numeUser = "";
        if (!contLogatList.isEmpty()) {
            ContLogat contLogat = contLogatList.get(0);  // Presupunem că există doar un singur utilizator logat
            numeUser = contLogat.getNume();
        }
        model.addAttribute("nume", numeUser);
        model.addAttribute("produseUser", repository.findAllByVanzator(numeUser));
        return "produseUser";
    }

    @GetMapping("/adaugare_produs")
    public String adaugareProdus(@RequestParam String denumire, @RequestParam float pret, @RequestParam String descriere, @RequestParam(required = false) Boolean negociabil){
        Produs produs = new Produs();
        produs.setDenumire(denumire);
        produs.setPret(pret);
        produs.setDescriere(descriere);
        produs.setNegociabil(negociabil != null ? negociabil : false);
        List<ContLogat> contlogat = contLogatRepository.findAll();
        ContLogat contLogat = contlogat.stream().findFirst().get();
        produs.setVanzator(contLogat.getNume());
        repository.save(produs);

        return "redirect:/produse";
    }
    @GetMapping("/sterge")
    public String stergeProdus(@RequestParam int id) {
        repository.deleteById(id);
        return "redirect:/produse";
    }

    @GetMapping("/sterge1")
    public String stergeProdus1(@RequestParam int id) {
        repository.deleteById(id);
        return "redirect:/produse_user";
    }

    @GetMapping("/cumpara")
    public String adaugaInCos(@RequestParam int id)
    {
        Optional<Produs> produs = repository.findById(id); //aflam produsul pe care l-a cumparat
        List<ContLogat> contlogat = contLogatRepository.findAll(); // aflam contul care este logat
        ContLogat contLogat = contlogat.stream().findFirst().get();

        ProdusInCos produsInCos = new ProdusInCos(); //cream produsul care se va introduce in cos
        produsInCos.setId(id);
        produsInCos.setIdCumparator(contLogat.getId());
        produsInCos.setDenumire(produs.get().getDenumire());
        produsInCos.setPret(produs.get().getPret());
        produsInCos.setNegociabil(produs.get().getNegociabil());
        produsInCos.setDescriere(produs.get().getDescriere());
        produsInCos.setVanzator(produs.get().getVanzator());

        produsInCosRepository.save(produsInCos); //se salveaza in baza de date
        return "redirect:/produse";
    }

    @GetMapping("/cos")
    public String cosCumparaturi(Model model)
    {
        List<ContLogat> contLogatList = contLogatRepository.findAll();
        ContLogat contLogat = contLogatList.get(0);
        float suma=0;
        List<ProdusInCos> produseInCos = produsInCosRepository.findAllByIdCumparator(contLogat.getId());
        for (ProdusInCos produs : produseInCos) {
            suma += produs.getPret();
        }
        model.addAttribute("total_plata", suma);
        model.addAttribute("produse", produsInCosRepository.findAllByIdCumparator(contLogat.getId()));

        return "cosCumparaturi";
    }
    @GetMapping("sterge_din_cos")
    public String cosCumparaturi(@RequestParam int id)
    {
        produsInCosRepository.deleteById(id);
        return "redirect:/cos";
    }

    @GetMapping("/accepta_oferta")
    public String acceptOffer(@RequestParam int id) {
        Optional<Produs> optionalProdus = repository.findById(id);

        if (optionalProdus.isPresent()) {
            Produs produs = optionalProdus.get();
            produs.setVanzator(null);


            repository.save(produs);
        }

        return "redirect:/produse";
    }

    @Autowired
    private ProdusService produsService;

    @PostMapping("/ofera")
    public String submitOffer(@RequestParam("id") int produsId, @RequestParam("oferta") float oferta) {
        produsService.addOffer(produsId, oferta);
        return "redirect:/produse";
    }

    @PostMapping("/accepta_oferta")
    public String acceptOffer(@RequestParam("id") int produsId, @RequestParam("oferta") float oferta) {
        produsService.acceptOffer(produsId, oferta);
        return "redirect:/produse";
    }




}
