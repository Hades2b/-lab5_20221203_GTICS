package com.example.lab5_20221203.controller;

import com.example.lab5_20221203.entity.*;
import com.example.lab5_20221203.repository.MensajeRepository;
import com.example.lab5_20221203.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller

public class UsuarioController {

    final UsuarioRepository usuarioRepository;
    final MensajeRepository mensajeRepository;

    public UsuarioController(UsuarioRepository usuarioRepository, MensajeRepository mensajeRepository) {
        this.usuarioRepository = usuarioRepository;
        this.mensajeRepository = mensajeRepository;
    }

    @GetMapping("")
    public String listarUsuarios(Model model) {

//        Usuario usuario = usuarioRepository.findByEmail(a);

        List<Usuario> usuarios = usuarioRepository.findAll();
        model.addAttribute("listaUsuarios", usuarios);

        return "lista";
    }


    @GetMapping("/nuevoMensaje/{id}")
    public String nuevoMensaje(Model model, @PathVariable Integer id) {

        Optional<Usuario> usuarioDest = usuarioRepository.findById(id);
        if (usuarioDest.isPresent()) {
            Mensaje mensaje = new Mensaje();
            mensaje.setDestinatario(usuarioDest.get());
            model.addAttribute("mensaje", mensaje);
        }

        return "nuevoMensaje";
    }

    @PostMapping("/enviarMensaje")
    public String enviarMensaje(@ModelAttribute Mensaje mensaje) {
        mensajeRepository.save(mensaje);
        return "redirect:/";
    }


}
