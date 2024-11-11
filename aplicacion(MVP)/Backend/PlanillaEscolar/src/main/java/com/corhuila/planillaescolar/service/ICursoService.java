package com.corhuila.planillaescolar.service;

import com.corhuila.planillaescolar.entity.Curso;

import java.util.List;

public interface ICursoService {

    public List<Curso> findAll();

    public Curso findById(Long id);

    public Curso update(Curso Curso, Long id);

    public Curso save (Curso Curso);

    public void delete(Long id);
}
