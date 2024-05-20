package com.magazin.Service;

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
}
