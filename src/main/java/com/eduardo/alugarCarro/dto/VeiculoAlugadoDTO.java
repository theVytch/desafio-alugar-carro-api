package com.eduardo.alugarCarro.dto;

import com.eduardo.alugarCarro.entities.Veiculo;
import com.eduardo.alugarCarro.entities.VeiculoAlugado;
import com.eduardo.alugarCarro.entities.enums.EstadoVeiculo;

import java.io.Serializable;
import java.util.Date;

public class VeiculoAlugadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;
    private Date inicioReserva;
    private Date fimReserva;
    private EstadoVeiculo estado;

    private Veiculo veiculo;

    public VeiculoAlugadoDTO(){}

    public VeiculoAlugadoDTO(VeiculoAlugado obj) {
        id = obj.getId();
        inicioReserva = obj.getInicioReserva();
        fimReserva = obj.getFimReserva();
        estado = obj.getEstado();
        veiculo = obj.getVeiculo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInicioReserva() {
        return inicioReserva;
    }

    public void setInicioReserva(Date inicioReserva) {
        this.inicioReserva = inicioReserva;
    }

    public Date getFimReserva() {
        return fimReserva;
    }

    public void setFimReserva(Date fimReserva) {
        this.fimReserva = fimReserva;
    }

    public EstadoVeiculo getEstado() {
        return estado;
    }

    public void setEstado(EstadoVeiculo estado) {
        this.estado = estado;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
