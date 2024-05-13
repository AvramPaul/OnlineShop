package com.magazin.entityProdus;
import com.magazin.entityCont.Cont;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="produs")
public class Produs {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String denumire;
    private float pret;
    private Boolean negociabil;
    private String descriere;
    private String vanzator;
}