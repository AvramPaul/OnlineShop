package com.magazin.controller;


import com.magazin.entityCont.Cont;
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


}
