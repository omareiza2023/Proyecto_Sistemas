package com.corhuila.planillaescolar.service;

import com.corhuila.planillaescolar.entity.Rol;
import com.corhuila.planillaescolar.repositories.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    private IRolRepository rolRepository;


    @Override
    public List<Rol> findAll() {
        return (List<Rol>) rolRepository.findAll();
    }

    @Override
    public Rol findById(Long id) {
        return rolRepository.findById(id).orElse(null);
    }

    @Override
    public Rol save(Rol Rol) {
        return rolRepository.save(Rol);
    }

    @Override
    public void delete(Long id) {
        rolRepository.deleteById(id);
    }

    @Override
    public Rol update(Rol rol, Long id) {
        Optional<Rol> rolExistente = rolRepository.findById(id);
        if (rolExistente.isPresent()) {
            Rol rolActual = rolExistente.get();
            rolActual.setRolName(rol.getRolName());
            rolActual.setDescription(rol.getDescription());
            return rolRepository.save(rolActual);
        } else {
            throw new RuntimeException("Rol no encontrado con el id " + id);
        }
    }
}
