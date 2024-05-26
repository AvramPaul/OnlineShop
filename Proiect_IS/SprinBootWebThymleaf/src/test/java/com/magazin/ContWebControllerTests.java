package com.magazin;

import com.magazin.Service.UserService;
import com.magazin.controller.ContWebController;
import com.magazin.entityCont.Cont;
import com.magazin.entityCont.ContAnulat;
import com.magazin.entityCont.ContLogat;
import com.magazin.repositoryCont.ContAnulatRepository;
import com.magazin.repositoryCont.ContLogatRepository;
import com.magazin.repositoryCont.ContRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ContWebControllerTests<ViorelSoitu> {

    @InjectMocks
    private ContWebController contWebController;

    @Mock
    private ContRepository contRepository;
    @Mock
    private ContAnulatRepository contAnulatRepository;
    @Mock
    private ContLogatRepository contLogatRepository;
    @Mock
    private Model model;
    @Mock
    UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetListaConturi() {
        List<Cont> conturi = new ArrayList<>();
        conturi.add(new Cont());
        when(contRepository.findAll()).thenReturn(conturi);

        String viewName = contWebController.getListaConturi(model);

        assertEquals("listaConturi", viewName);
        verify(model).addAttribute("conturi", conturi);
    }

    @Test
    public void testAdaugareCont() {
        String nume = "John";
        String email = "john@example.com";
        String parola = "password";

        String viewName = contWebController.adaugareCont(nume, email, parola);

        assertEquals("redirect:/conturi", viewName);
        verify(contRepository).save(any(Cont.class));
    }

    @Test
    public void testDeleteAccount() {
        Integer id = 1;
        Cont cont = new Cont();
        cont.setId(id);

        when(contRepository.findById(id)).thenReturn(Optional.of(cont));

        String viewName = contWebController.deleteAccount(id);

        assertEquals("redirect:/conturi", viewName);
        verify(contRepository).deleteById(id);

    }

    @Test
    public void testGetListaConturiWithNoData() {
        when(contRepository.findAll()).thenReturn(new ArrayList<>());


        String viewName = contWebController.getListaConturi(model);


        assertEquals("listaConturi", viewName);
        verify(model).addAttribute("conturi", new ArrayList<>());
    }

    @Test
    public void testAdaugareContWithNullParams() {

        String viewName = contWebController.adaugareCont(null, null, null);


        assertEquals("redirect:/conturi", viewName);
        verify(contRepository, never()).save(any(Cont.class));
    }



}
