package com.magazin.entityProdus;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="ProduseInCos")
public class ProdusInCos {
    @Id
    private int id;
    private int idCumparator;
    private String denumire;
    private float pret;
    private Boolean negociabil;
    private String descriere;
    private String vanzator;
}
