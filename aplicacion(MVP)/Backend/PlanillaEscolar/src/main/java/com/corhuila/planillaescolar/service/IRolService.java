package com.corhuila.planillaescolar.service;

import com.corhuila.planillaescolar.entity.Rol;

import java.util.List;

public interface IRolService {

    public List<Rol> findAll();

    public Rol findById(Long id);

    public Rol update(Rol Rol, Long id);

    public Rol save (Rol Rol);

    public void delete(Long id);
}
