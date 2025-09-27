package com.example.lab5_20221203.controller;

import com.example.lab5_20221203.entity.Mensaje;
import com.example.lab5_20221203.entity.Usuario;
import com.example.lab5_20221203.repository.MensajeRepository;
import com.example.lab5_20221203.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mensajes")
public class MensajeController {

    final UsuarioRepository usuarioRepository;
    final MensajeRepository mensajeRepository;

    public MensajeController(UsuarioRepository usuarioRepository, MensajeRepository mensajeRepository) {
        this.usuarioRepository = usuarioRepository;
        this.mensajeRepository = mensajeRepository;
    }

    @GetMapping("/nuevoMensaje/{id}")
    public String nuevoMensaje(Model model, @PathVariable Integer id, HttpSession session) {
        Usuario usuarioActual = (Usuario) session.getAttribute("usuario");

        Optional<Usuario> usuarioDest = usuarioRepository.findById(id);
        if (usuarioDest.isPresent()) {
            Mensaje mensaje = new Mensaje();
            mensaje.setDestinatario(usuarioDest.get());
            mensaje.setRemitente(usuarioActual);
            mensaje.setFechaEnvio(new Timestamp(System.currentTimeMillis()));
            model.addAttribute("mensaje", mensaje);
        } else {
            return "redirect:/usuarios";
        }

        return "nuevoMensaje";
    }

    @PostMapping("/enviarMensaje")
    public String enviarMensaje(@ModelAttribute Mensaje mensaje, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuario usuarioActual = (Usuario) session.getAttribute("usuario");
        if (usuarioActual != null && !mensaje.getRemitente().equals(usuarioActual)) {
            mensaje.setRemitente(usuarioActual);
        }
        mensaje.setFechaEnvio(new Timestamp(System.currentTimeMillis()));
        mensajeRepository.save(mensaje);
        redirectAttributes.addFlashAttribute("msg", "Mensaje enviado correctamente");
        return "redirect:/usuarios";
    }

    @GetMapping("/bandeja")
    public String mostrarListaMensajes(Model model, HttpSession session) {
        Usuario usuarioActual = (Usuario) session.getAttribute("usuario");

        if (usuarioActual != null) {
            List<Mensaje> listMensajes = mensajeRepository.findByDestinatario_id(usuarioActual.getId());
            model.addAttribute("listaMensajes", listMensajes);
            model.addAttribute("totalMensajes", listMensajes.size());

            model.addAttribute("usuario", usuarioActual);
        } else {

            List<Mensaje> listMensajes = mensajeRepository.findAll();
            model.addAttribute("listaMensajes", listMensajes);
            model.addAttribute("totalMensajes", listMensajes.size());
        }

        return "bandeja";
    }

    @GetMapping("/{id}")
    public String mostrarMensaje(Model model, @PathVariable Integer id, HttpSession session) {
        Usuario usuarioActual = (Usuario) session.getAttribute("usuario");

        Optional<Mensaje> mensaje = mensajeRepository.findById(id);
//        if (mensaje.isPresent() && mensaje.get().getRemitente().equals(usuarioActual)) {
            model.addAttribute("mensaje", mensaje.get());
//        } else return "redirect:/mensajes";
        return "mensajeDetalle";
    }
}
