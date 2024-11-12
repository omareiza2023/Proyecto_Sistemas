package com.corhuila.planillaescolar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nota")
@Entity
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "corte_1", length = 100)
    private Double corte1;

    @Column(name = "corte_2", length = 100)
    private  Double corte2;

    @Column(name = "corte_3", length = 100)
    private  Double corte3;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "materia_id", nullable = false, unique = true)
    private Materia materia;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCorte1() {
        return corte1;
    }

    public void setCorte1(Double corte1) {
        this.corte1 = corte1;
    }

    public Double getCorte2() {
        return corte2;
    }

    public void setCorte2(Double corte2) {
        this.corte2 = corte2;
    }

    public Double getCorte3() {
        return corte3;
    }

    public void setCorte3(Double corte3) {
        this.corte3 = corte3;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
