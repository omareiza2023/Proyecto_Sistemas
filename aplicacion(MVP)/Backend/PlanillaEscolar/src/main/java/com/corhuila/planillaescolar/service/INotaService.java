package com.corhuila.planillaescolar.service;

import com.corhuila.planillaescolar.entity.Nota;

import java.util.List;

public interface INotaService {

    public List<Nota> findAll();

    public Nota findById(Long id);

    public Nota update(Nota Nota, Long id);

    public Nota save (Nota Nota);

    public void delete(Long id);
}
