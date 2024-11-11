package com.proyecto.GestionContactos.IRepository;


import com.proyecto.GestionContactos.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = ?1 AND u.contrasenia = ?2")
    Optional<Usuario> findByNombreUsuarioAndContrasenia(String nombreUsuario, String contrasenia);
    Optional<Usuario> findById(Integer id);
}
