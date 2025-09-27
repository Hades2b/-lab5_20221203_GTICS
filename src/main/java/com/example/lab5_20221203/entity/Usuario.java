package com.example.lab5_20221203.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false)
    @NotNull(message = "Debe tener un nombre y apellido")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")
    private String nombre;

    @Column(name = "apellido", nullable = false)
    @NotNull(message = "Debe tener un nombre y apellido")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")
    private String apellido;

    @Column(name = "edad", nullable = false)
    @NotNull
    @Min(value = 18,message = "Debe ser mayor de edad")
    private Integer edad;

    @Column(name = "descripcion")
    @Size(min=10, max=100, message = "Su descripción debe tener más de 10 caracteres")
    private String descripcion;

    @Column(name = "correo", nullable = false)
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String correo;

    @Column(name = "contrasena")
    @NotNull
    @Size(min=6, message = "Su contraseña debe tener 6 caracteres como mínimo")
    @Pattern(regexp = ".*\\d.*", message = "La contraseña debe contener al menos un número")
    private String contrasena;

    // Relaciones con otras entidades
    @OneToMany(mappedBy = "remitente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mensaje> mensajesEnviados;

    @OneToMany(mappedBy = "destinatario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mensaje> mensajesRecibidos;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
