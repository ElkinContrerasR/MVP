package com.proyecto.GestionContactos.IService;

import com.proyecto.GestionContactos.Entity.Contacto;


import java.util.List;
import java.util.Optional;

public interface IContactoService {

    //Guardar
    Contacto save(Contacto contacto);

    //Actualizar
    void update(Contacto contacto, Integer id);

    //Mostrartodo
    List<Contacto> all();
    Optional<Contacto> findById(Integer id);

    //Eliminar
    void delete(Integer id);

    //Mostrar por nombre
    List<Contacto> findByUsuario(String nombreUsuario);



};
