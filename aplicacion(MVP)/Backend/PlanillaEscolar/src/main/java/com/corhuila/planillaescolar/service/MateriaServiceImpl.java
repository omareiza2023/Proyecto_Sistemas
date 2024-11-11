package com.corhuila.planillaescolar.service;

import com.corhuila.planillaescolar.entity.Materia;
import com.corhuila.planillaescolar.repositories.IMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaServiceImpl implements IMateriaService {

    @Autowired
    private IMateriaRepository materiaRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Materia> findAll() {
        return (List<Materia>) materiaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Materia findById(Long id) {
        return materiaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Materia save(Materia materia) {
        return materiaRepository.save(materia);
    }

    @Override
    public void delete(Long id) {
        materiaRepository.deleteById(id);
    }

    @Override
    public Materia update(Materia materia, Long materia_id) {
        Optional<Materia> op = materiaRepository.findById(materia_id);
        if (op.isPresent()) {
            Materia materiaActual = op.get();
            materiaActual.setNameMateria(materia.getNameMateria());
            materiaActual.setDescription(materia.getDescription());
            return materiaRepository.save(materiaActual);
        } else {
            throw new RuntimeException("Registro no encontrado");
        }
    }
}
