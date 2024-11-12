package com.proyecto.GestionContactos.Controller;

import com.proyecto.GestionContactos.Entity.Contacto;
import com.proyecto.GestionContactos.Entity.Usuario;
import com.proyecto.GestionContactos.IRepository.IUsuarioRepository;
import com.proyecto.GestionContactos.IService.IContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/contacto")

public class ContactoController {
    @Autowired
    private IContactoService service;

    @Autowired
    private IUsuarioRepository usuarioRepository; // Inyecta el repositorio de Usuario

    @PostMapping("")
    public Contacto save(@RequestBody Contacto contacto) {
        //Busca el usuario por ID y asigna el objeto Usuario al contacto
        Usuario usuario = usuarioRepository.findById(contacto.getUsuarioId().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        contacto.setUsuarioId(usuario); // Asigna el usuario al contacto
        return service.save(contacto);

    }

    @PutMapping("/{id}")
    public void update(@RequestBody Contacto contacto, @PathVariable Integer id) {
        service.update(contacto, id);
    }

    /*@GetMapping("")
    public List<Contacto> findByUsuario(@RequestParam String nombreUsuario) {
        return service.findByUsuario(nombreUsuario);
    }*/
    @GetMapping("")
    public List<Contacto> all(){
        return service.all();
    }



    @GetMapping("/{id}")
    public Optional<Contacto> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}
