package com.corhuila.planillaescolar.service;

import com.corhuila.planillaescolar.entity.Nota;
import com.corhuila.planillaescolar.repositories.INotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaServiceImpl implements INotaService {

    @Autowired
    private INotaRepository notaRepository;


    @Override
    public List<Nota> findAll() {
        return (List<Nota>) notaRepository.findAll();
    }

    @Override
    public Nota findById(Long id) {
        return notaRepository.findById(id).orElse(null);
    }

    @Override
    public Nota save(Nota Nota) {
        return notaRepository.save(Nota);
    }

    @Override
    public void delete(Long id) {
        notaRepository.deleteById(id);
    }

    @Override
    public Nota update(Nota nota, Long id) {
        Optional<Nota> notaExistente = notaRepository.findById(id);
        if (notaExistente.isPresent()) {
            Nota notaActual = notaExistente.get();
            notaActual.setCorte1(nota.getCorte1());
            notaActual.setCorte2(nota.getCorte2());
            notaActual.setCorte3(nota.getCorte3());
            notaActual.setMateria(nota.getMateria());
            notaActual.setUsuario(nota.getUsuario());
            return notaRepository.save(notaActual);
        } else {
            throw new RuntimeException("Nota no encontrada con el id " + id);
        }
    }
}
