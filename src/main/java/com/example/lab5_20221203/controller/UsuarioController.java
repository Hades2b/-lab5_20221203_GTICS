package com.example.lab5_20221203.controller;

import com.example.lab5_20221203.entity.*;
import com.example.lab5_20221203.repository.MensajeRepository;
import com.example.lab5_20221203.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model, HttpSession session) {
        Usuario usuarioActual = (Usuario) session.getAttribute("usuario");

        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarioActual != null) {
            usuarios.remove(usuarioActual);
        } else {
            model.addAttribute("faltaSesion", true);
        }

        model.addAttribute("listaUsuarios", usuarios);

        return "lista";
    }

    @GetMapping("/registro")
    public String mostrarVistaRegistro(Model model, @ModelAttribute Usuario usuario) {

        model.addAttribute("usuario", usuario);
        return "registro";
    }

    @PostMapping("/registrar")
    public String registrarUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpSession session) {

        if (result.hasErrors()) {
            return "registro";
        }

        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        session.setAttribute("usuario", nuevoUsuario);

        redirectAttributes.addFlashAttribute("msg", "Usuario creado exitosamente");
        return "redirect:/usuarios";
    }




}
