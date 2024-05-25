package com.magazin.entityCont;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="cont_anulat")
public class ContAnulat {
    @Id
    private int id;
    private String email;
    private String parola;
    private String nume;
    private String role;
}
