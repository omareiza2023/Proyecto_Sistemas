package com.corhuila.planillaescolar.service;

import com.corhuila.planillaescolar.entity.Usuario;

import java.util.List;

public interface IUsuarioService {

    public List<Usuario> findAll();

    public Usuario findById(Long id);

    public Usuario update(Usuario Usuario, Long id);

    public Usuario save (Usuario Usuario);

    public void delete(Long id);
}
