package com.proyecto.GestionContactos.IService;


import com.proyecto.GestionContactos.Entity.Usuario;


import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    //Guardar
    Usuario save(Usuario usuario);

    //Actualizar
    void update(Usuario usuario, Integer id);

    //Mostrartodo
    List<Usuario> all();

    Optional<Usuario> findById(Integer id);

    //Eliminar
    void delete(Integer id);


    Optional<Usuario> authenticate(String nombreUsuario, String contrasenia);  // Nuevo método






};
