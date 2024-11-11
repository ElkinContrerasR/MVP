package com.proyecto.GestionContactos.Service;

import com.proyecto.GestionContactos.Entity.Contacto;
import com.proyecto.GestionContactos.IRepository.IContactoRepository;
import com.proyecto.GestionContactos.IService.IContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ContactoService implements IContactoService {
    @Autowired
    private IContactoRepository repository;
    @Override
    public Contacto save(Contacto contacto) {
        return repository.save(contacto);
    }

    @Override
    public void update(Contacto contacto, Integer id) {
        Optional<Contacto> up = repository.findById(id);
       if(up.isPresent()){
           Contacto contactoActual = up.get();
           contactoActual.setNombres(contacto.getNombres());
           contactoActual.setApellidos(contacto.getApellidos());
           contactoActual.setTelefono(contacto.getTelefono());
           repository.save(contactoActual);
       }else{
           System.out.println("No existe registro de contacto");
       }
    }

    @Override
    public List<Contacto> all() {
        return repository.findAll();
    }

    @Override
    public Optional<Contacto> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Contacto> findByUsuario(String nombreUsuario) {
        return repository.findByUsuarioId_NombreUsuario(nombreUsuario);
    }



}
