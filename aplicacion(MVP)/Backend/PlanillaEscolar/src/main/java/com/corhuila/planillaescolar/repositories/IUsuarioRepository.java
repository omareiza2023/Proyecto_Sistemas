package com.corhuila.planillaescolar.repositories;

import com.corhuila.planillaescolar.entity.Nota;
import com.corhuila.planillaescolar.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioRepository extends CrudRepository<Usuario,Long> {



}
