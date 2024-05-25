package com.magazin.controller;

import com.magazin.Service.UserService;
import com.magazin.entityCont.Cont;
import com.magazin.entityCont.ContAnulat;
import com.magazin.entityCont.ContLogat;
import com.magazin.repositoryCont.ContAnulatRepository;
import com.magazin.repositoryCont.ContLogatRepository;
import com.magazin.repositoryCont.ContRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ContWebController {
    @Autowired
    ContRepository repository;

    @Autowired
    ContLogatRepository contLogatRepository;

    @Autowired
    ContAnulatRepository contAnulatRepository;

    @GetMapping("/conturi")
    public String getListaConturi(Model model) {
        model.addAttribute("conturi", repository.findAll());
        return "listaConturi";
    }

    @PostMapping("/adaugare_cont")
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
        Optional<Cont> cont;
        cont = repository.findById(id);
        ContAnulat ca = new ContAnulat();
        ca.setEmail(cont.get().getEmail());
        ca.setNume(cont.get().getNume());
        ca.setRole(cont.get().getRole());
        ca.setParola(cont.get().getParola());
        ca.setId(cont.get().getId());
        contAnulatRepository.save(ca);
        repository.deleteById(id);
        return "redirect:/conturi";
    }

    @GetMapping("/register")
    public String loadRegisterPage() {
        return "RegisterPage";
    }

    @GetMapping("/inregistrare_cont")
    public String inregistrareCont(@RequestParam String email, @RequestParam String parola, @RequestParam String nume, @RequestParam String role) {
        Cont cont = new Cont();
        cont.setEmail(email);
        cont.setParola(parola);
        cont.setNume(nume);
        cont.setRole(role);

        repository.save(cont);

        return "redirect:/register";
    }
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        contLogatRepository.deleteAll();
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        if (userService.isValidUser(email, password)) {
            // User is valid, proceed with login
            // Here you might set up session or token-based authentication
            contLogatRepository.save(userService.whichUser(email, password));
            System.out.println();
            return "redirect:/produse";
        } else {
            // Invalid credentials, redirect back to login page with an error message
            return "redirect:/login?error";
        }
    }
}
