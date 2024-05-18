package com.magazin.entityCont;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="cont")
public class Cont {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String parola;
    private String nume;
<<<<<<< Updated upstream
=======
    private String role;

//    public Cont(int i, String john, String mail, String password, String role) {
//    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getParola() {
//        return parola;
//    }
//
//    public void setParola(String parola) {
//        this.parola = parola;
//    }
//
//    public String getRole(){ return this.role; }
//    public void setRole(String role){ this.role = role; }
>>>>>>> Stashed changes
}