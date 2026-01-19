package com.ens.crud.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "autoriz")
public class Autoriz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;

    @ManyToOne
    @JoinColumn(name = "seccion_id", nullable = false)
    private Seccion seccion;

    @Column(name = "puede_leer")
    private Boolean puedeLeer;

    @Column(name = "puede_escribir")
    private Boolean puedeEscribir;

    @Column(name = "puede_eliminar")
    private Boolean puedeEliminar;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        if (activo == null) {
            activo = true;
        }
        if (puedeLeer == null) {
            puedeLeer = false;
        }
        if (puedeEscribir == null) {
            puedeEscribir = false;
        }
        if (puedeEliminar == null) {
            puedeEliminar = false;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        fechaModificacion = LocalDateTime.now();
    }

    // Constructors
    public Autoriz() {
    }

    public Autoriz(Perfil perfil, Seccion seccion, Boolean puedeLeer, Boolean puedeEscribir, Boolean puedeEliminar) {
        this.perfil = perfil;
        this.seccion = seccion;
        this.puedeLeer = puedeLeer;
        this.puedeEscribir = puedeEscribir;
        this.puedeEliminar = puedeEliminar;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public Boolean getPuedeLeer() {
        return puedeLeer;
    }

    public void setPuedeLeer(Boolean puedeLeer) {
        this.puedeLeer = puedeLeer;
    }

    public Boolean getPuedeEscribir() {
        return puedeEscribir;
    }

    public void setPuedeEscribir(Boolean puedeEscribir) {
        this.puedeEscribir = puedeEscribir;
    }

    public Boolean getPuedeEliminar() {
        return puedeEliminar;
    }

    public void setPuedeEliminar(Boolean puedeEliminar) {
        this.puedeEliminar = puedeEliminar;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
