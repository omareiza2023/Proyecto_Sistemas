package com.corhuila.planillaescolar.service;

import com.corhuila.planillaescolar.entity.Usuario;
import com.corhuila.planillaescolar.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;


    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario save(Usuario Usuario) {
        return usuarioRepository.save(Usuario);
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario update(Usuario usuario, Long id) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuarioActual = usuarioExistente.get();
            usuarioActual.setUsername(usuario.getUsername());
            usuarioActual.setPassword(usuario.getPassword());
            usuarioActual.setRol(usuario.getRol());
            usuarioActual.setPersonaId(usuario.getPersonaId());
            return usuarioRepository.save(usuarioActual);
        } else {
            throw new RuntimeException("Usuario no encontrado con el id " + id);
        }
    }
}
