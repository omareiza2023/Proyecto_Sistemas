package com.corhuila.planillaescolar.service;

import com.corhuila.planillaescolar.entity.Persona;

import java.util.List;
import java.util.Optional;

public interface IPersonaService {

    public List<Persona> findAll();

    public Persona findById(Long id);

    public Persona update(Persona Persona, Long id);

    public Persona save (Persona Persona);

    public void delete(Long id);
}
