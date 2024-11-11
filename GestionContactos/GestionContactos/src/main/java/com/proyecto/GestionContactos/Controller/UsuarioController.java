package com.proyecto.GestionContactos.Controller;


import com.proyecto.GestionContactos.Entity.Usuario;

import com.proyecto.GestionContactos.IService.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RestController
@RequestMapping("/api/usuario")

public class UsuarioController {
    @Autowired
    private IUsuarioService service;


    @PostMapping("")
    public Usuario save(@RequestBody Usuario usuario) {
        return service.save(usuario);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Usuario usuario, @PathVariable Integer id) {
        service.update(usuario, id);
    }

    @GetMapping("")
    public List<Usuario> all(){
        return service.all();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    // UsuarioController.java
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Optional<Usuario> authenticatedUser = service.authenticate(usuario.getNombreUsuario(), usuario.getContrasenia());
        if (authenticatedUser.isPresent()) {
            return ResponseEntity.ok("Login exitoso");
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }



}
