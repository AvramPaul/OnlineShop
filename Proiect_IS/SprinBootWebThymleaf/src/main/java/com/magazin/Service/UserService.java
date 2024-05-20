package com.magazin.Service;

import com.magazin.entityCont.ContLogat;
import com.magazin.repositoryCont.ContLogatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {



    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isValidUser(String email, String password) {
        // Query to check if the user exists in the database
        String sql = "SELECT COUNT(*) FROM cont WHERE email = ? AND parola = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, email, password);
        return count == 1;
    }

    public ContLogat whichUser(String email, String password) {
        String sql = "SELECT id FROM cont WHERE email = ? AND parola = ?";
        int id = jdbcTemplate.queryForObject(sql, Integer.class, email, password);
        sql = "SELECT email FROM cont WHERE email = ? AND parola = ?";
        String email1 = jdbcTemplate.queryForObject(sql, String.class, email, password);
        sql = "SELECT parola FROM cont WHERE email = ? AND parola = ?";
        String parola = jdbcTemplate.queryForObject(sql, String.class, email, password);
        sql = "SELECT nume FROM cont WHERE email = ? AND parola = ?";
        String nume = jdbcTemplate.queryForObject(sql, String.class, email, password);
        sql = "SELECT role FROM cont WHERE email = ? AND parola = ?";
        String role = jdbcTemplate.queryForObject(sql, String.class, email, password);
        ContLogat cont = new ContLogat();
        cont.setId(id);
        cont.setEmail(email1);
        cont.setParola(parola);
        cont.setNume(nume);
        cont.setRole(role);
        return cont;
    }
}
