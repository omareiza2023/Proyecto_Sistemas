package com.corhuila.planillaescolar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Table(name = "persona")
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_persona")
    private String namePersona;

    @Column(name = "mail_persona")
    private String mailPersona;

    @Column(name = "num_persona")
    private Integer numPersona;

    @Column(name = "direccion", length = 100)
    private String Direccion;

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public Integer getNumPersona() {
        return numPersona;
    }

    public void setNumPersona(Integer numPersona) {
        this.numPersona = numPersona;
    }

    public String getMailPersona() {
        return mailPersona;
    }

    public void setMailPersona(String mailPersona) {
        this.mailPersona = mailPersona;
    }

    public String getNamePersona() {
        return namePersona;
    }

    public void setNamePersona(String namePersona) {
        this.namePersona = namePersona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
