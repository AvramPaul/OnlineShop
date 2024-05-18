package com.magazin.controller;


import com.magazin.entityCont.Cont;
import com.magazin.entityProdus.Produs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.magazin.repositoryCont.ContRepository;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContWebController {
    @Autowired
    ContRepository repository;

<<<<<<< Updated upstream
=======
    @GetMapping("/conturi")
    public String getListaConturi(Model model)
    {
        model.addAttribute("conturi", repository.findAll());
        return "listaConturi";
    }

    @GetMapping("/adaugare_cont")
    public String adaugareCont(@RequestParam String nume, @RequestParam String email, @RequestParam String parola) {
        Cont cont = new Cont();
        cont.setNume(nume);
        cont.setEmail(email);
        cont.setParola(parola);

        repository.save(cont);

        return "redirect:/conturi";
    }
    @GetMapping("/stergere_cont")
    public String deleteAccount(@RequestParam("id") Integer id) {
        //delete the account by id
        repository.deleteById(id);

        return "redirect:/conturi";
    }

    @GetMapping("/register")
    public String inregistrareCont(@RequestParam String email, @RequestParam String parola, @RequestParam String nume, @RequestParam String role) {
        Cont cont = new Cont();
        cont.setEmail(email);
        cont.setParola(parola);
        cont.setNume(nume);
        cont.setRole(role);

        repository.save(cont);

        return "inregistrare_cont";
    }
>>>>>>> Stashed changes

}
