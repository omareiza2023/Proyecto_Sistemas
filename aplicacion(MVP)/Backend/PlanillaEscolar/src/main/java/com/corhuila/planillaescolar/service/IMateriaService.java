package com.corhuila.planillaescolar.service;

import com.corhuila.planillaescolar.entity.Materia;

import java.util.List;


public interface IMateriaService {

    public List<Materia> findAll();

    public Materia findById(Long id);

    public Materia update(Materia materia, Long id);

    public Materia save (Materia materia);

    public void delete(Long id);


}
