package com.corhuila.planillaescolar.service;

import com.corhuila.planillaescolar.entity.Persona;
import com.corhuila.planillaescolar.repositories.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    private IPersonaRepository personaRepository;


    @Override
    public List<Persona> findAll() {
        return (List<Persona>) personaRepository.findAll();
    }

    @Override
    public Persona findById(Long id) {
        return personaRepository.findById(id).orElse(null);
    }

    @Override
    public Persona save(Persona Persona) {
        return personaRepository.save(Persona);
    }

    @Override
    public void delete(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public Persona update(Persona persona, Long id) {
        Optional<Persona> personaExistente = personaRepository.findById(id);
        if (personaExistente.isPresent()) {
            Persona personaActual = personaExistente.get();
            personaActual.setNamePersona(persona.getNamePersona());
            personaActual.setMailPersona(persona.getMailPersona());
            personaActual.setNumPersona(persona.getNumPersona());
            personaActual.setDireccion(persona.getDireccion());
            return personaRepository.save(personaActual);
        } else {
            throw new RuntimeException("Persona no encontrada con el id " + id);
        }
    }
}
