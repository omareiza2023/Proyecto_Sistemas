package com.corhuila.planillaescolar.repositories;

import com.corhuila.planillaescolar.entity.Persona;
import org.springframework.data.repository.CrudRepository;

public interface IPersonaRepository extends CrudRepository<Persona,Long> {



}
