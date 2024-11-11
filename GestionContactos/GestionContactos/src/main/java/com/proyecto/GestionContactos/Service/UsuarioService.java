package com.proyecto.GestionContactos.Service;


import com.proyecto.GestionContactos.Entity.Usuario;

import com.proyecto.GestionContactos.IRepository.IUsuarioRepository;

import com.proyecto.GestionContactos.IService.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private IUsuarioRepository repository;


    @Override
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public void update(Usuario usuario, Integer id) {
        Optional<Usuario> up = repository.findById(id);
        if(up.isPresent()){
            Usuario usuarioActual = up.get();
            usuarioActual.setNombres(usuario.getNombres());
            usuarioActual.setApellidos(usuario.getApellidos());
            usuarioActual.setNombreUsuario(usuario.getNombreUsuario());
            usuarioActual.setContrasenia(usuario.getContrasenia());

            repository.save(usuarioActual);
        }else{
            System.out.println("No existe el registro");
        }
    }

    @Override
    public List<Usuario> all() {
       return repository.findAll();
    }


    //Este método devuelve solo el usuario id
    @Override
    public Optional<Usuario> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    // UsuarioService.java
    //login
    public Optional<Usuario> authenticate(String nombreUsuario, String contrasenia) {
        return repository.findByNombreUsuarioAndContrasenia(nombreUsuario, contrasenia);
    }



}
