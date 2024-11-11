package com.proyecto.GestionContactos.IRepository;

import com.proyecto.GestionContactos.Entity.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IContactoRepository extends JpaRepository<Contacto, Integer> {

    List<Contacto> findByUsuarioId_NombreUsuario(String nombreUsuario);


}
