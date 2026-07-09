package com.example.Anteiku.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTrabajador;
    private String puesto;
    private String turno;

    @OneToMany(mappedBy = "trabajador")
    private List<pedido> pedidos;

    public Long getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Long idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public List<pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
