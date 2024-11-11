package com.corhuila.planillaescolar.service;

import com.corhuila.planillaescolar.entity.Curso;
import com.corhuila.planillaescolar.repositories.ICursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements ICursoService {

    @Autowired
    private ICursoRepository cursoRepository;


    @Override
    public List<Curso> findAll() {
        return (List<Curso>) cursoRepository.findAll();
    }

    @Override
    public Curso findById(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    @Override
    public Curso save(Curso Curso) {
        return cursoRepository.save(Curso);
    }

    @Override
    public void delete(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    public Curso update(Curso Curso, Long id) {
        Optional<Curso> cursoExistente = cursoRepository.findById(id);
        if (cursoExistente.isPresent()) {
            Curso cursoActual = cursoExistente.get();
            cursoActual.setNomCurso(Curso.getNomCurso());
            cursoActual.setDescripcion(Curso.getDescripcion());
            return cursoRepository.save(cursoActual);
        } else {
            throw new RuntimeException("Curso no encontrado con el id " + id);
        }
    }
}
