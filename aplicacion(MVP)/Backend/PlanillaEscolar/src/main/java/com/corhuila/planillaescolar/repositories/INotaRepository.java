package com.corhuila.planillaescolar.repositories;

import com.corhuila.planillaescolar.entity.Nota;
import com.corhuila.planillaescolar.entity.Rol;
import org.springframework.data.repository.CrudRepository;

public interface INotaRepository extends CrudRepository<Nota,Long> {



}
