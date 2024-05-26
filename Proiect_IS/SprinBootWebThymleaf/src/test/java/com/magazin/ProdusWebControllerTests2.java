package com.magazin;

import com.magazin.controller.ProdusWebController;
import com.magazin.entityCont.ContLogat;
import com.magazin.entityProdus.Produs;
import com.magazin.entityProdus.ProdusInCos;
import com.magazin.repositoryCont.ContLogatRepository;
import com.magazin.repositoryProdus.ProdusInCosRepository;
import com.magazin.repositoryProdus.ProdusRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProdusWebControllerTests2<Sarca_Denis>{
    @InjectMocks
    private ProdusWebController produsWebController;

    @Mock
    private ProdusRepository produsRepository;

    @Mock
    private ProdusInCosRepository produsInCosRepository;

    @Mock
    private ContLogatRepository contLogatRepository;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testStergeProdus1() {
        String viewName = produsWebController.stergeProdus1(1);

        assertEquals("redirect:/produse_user", viewName);
        verify(produsRepository).deleteById(1);
    }

    @Test
    public void testAdaugaInCos() {
        Produs produs = new Produs();
        produs.setId(1);
        when(produsRepository.findById(1)).thenReturn(Optional.of(produs));
        when(contLogatRepository.findAll()).thenReturn(List.of(new ContLogat()));

        String viewName = produsWebController.adaugaInCos(1);

        assertEquals("redirect:/produse", viewName);
        verify(produsInCosRepository).save(any(ProdusInCos.class));
    }

    @Test
    public void testCosCumparaturi() {
        List<ProdusInCos> produseInCos = new ArrayList<>();
        produseInCos.add(new ProdusInCos());
        when(contLogatRepository.findAll()).thenReturn(List.of(new ContLogat()));
        when(produsInCosRepository.findAllByIdCumparator(anyInt())).thenReturn(produseInCos);

        String viewName = produsWebController.cosCumparaturi(model);

        assertEquals("cosCumparaturi", viewName);
        verify(model).addAttribute("produse", produseInCos);
    }

    @Test
    public void testStergeDinCos() {
        String viewName = produsWebController.cosCumparaturi(1);

        assertEquals("redirect:/cos", viewName);
        verify(produsInCosRepository).deleteById(1);
    }

    @Test
    public void testAcceptOffer() {
        Produs produs = new Produs();
        produs.setId(1);
        when(produsRepository.findById(1)).thenReturn(Optional.of(produs));

        String viewName = produsWebController.acceptOffer(1);

        assertEquals("redirect:/produse", viewName);
        verify(produsRepository).save(any(Produs.class));
    }
}
