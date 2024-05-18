package com.magazin;

import com.magazin.controller.ContWebController;
import com.magazin.entityCont.Cont;
import com.magazin.repositoryCont.ContRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ContWebControllerTests {

    @InjectMocks
    private ContWebController contWebController;

    @Mock
    private ContRepository contRepository;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetListaConturi() {
        List<Cont> conturi = new ArrayList<>();
        conturi.add(new Cont(1, "John", "john@example.com", "password"));
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

    @Test
    public void testDeleteAccountWithNullId() {

        String viewName = contWebController.deleteAccount(null);

        
        assertEquals("redirect:/conturi", viewName);
        verify(contRepository, never()).deleteById(any());
    }
}
