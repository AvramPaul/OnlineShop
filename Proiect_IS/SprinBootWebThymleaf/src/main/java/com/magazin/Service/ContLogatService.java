package com.magazin.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ContLogatService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public String getRole()
    {
        String sql = "SELECT role FROM cont_logat";
        String role = jdbcTemplate.queryForObject(sql, String.class);
        return role;
    }
    public String getNume()
    {
        String sql = "SELECT nume FROM cont_logat";
        String nume = jdbcTemplate.queryForObject(sql, String.class);
        return nume;
    }
}
