package com.magazin.entityProdus;
import com.magazin.entityCont.Cont;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @ElementCollection
    @CollectionTable(name = "oferta", joinColumns = @JoinColumn(name = "produs_id"))
    @Column(name = "valoare")
    private List<Float> oferte;

    private Float ofertaAcceptata;
    private String cumparator;
}