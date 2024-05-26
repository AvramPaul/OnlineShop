package com.magazin;

import com.magazin.Service.UserService;
import com.magazin.controller.ContWebController;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

public class ContWebControllerTests2<Vanessa_Stef> {

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
    public void testLoadRegisterPage() {
        String viewName = contWebController.loadRegisterPage();

        assertEquals("RegisterPage", viewName);
    }

    @Test
    public void testDeleteAccountWithInvalidId() {
        Integer id = 999;
        when(contRepository.findById(id)).thenReturn(Optional.empty());

        String viewName = contWebController.deleteAccount(id);

        assertEquals("redirect:/conturi", viewName);
        verify(contAnulatRepository, never()).save(any(ContAnulat.class));
    }
    @Test
    public void testLoginWithValidUser() {
        String email = "valid@example.com";
        String password = "password";
        ContLogat contLogat = new ContLogat();
        when(userService.isValidUser(email, password)).thenReturn(true);
        when(userService.whichUser(email, password)).thenReturn(contLogat);

        String viewName = contWebController.login(email, password);

        assertEquals("redirect:/produse", viewName);
        verify(contLogatRepository).save(contLogat);
    }

    @Test
    public void testLoginWithInvalidUser() {
        String email = "invalid@example.com";
        String password = "password";
        when(userService.isValidUser(email, password)).thenReturn(false);

        String viewName = contWebController.login(email, password);

        assertEquals("redirect:/login?error", viewName);
        verify(contLogatRepository, never()).save(any(ContLogat.class));
    }
    @Test
    public void testDeleteAccountWithNullId() {

        String viewName = contWebController.deleteAccount(null);


        assertEquals("redirect:/conturi", viewName);
        verify(contRepository, never()).deleteById(any());
    }
}
