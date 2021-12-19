package com.eduardo.alugarCarro.dto;

import com.eduardo.alugarCarro.entities.Veiculo;
import com.eduardo.alugarCarro.entities.VeiculoAlugado;
import com.eduardo.alugarCarro.entities.enums.EstadoVeiculo;

import java.io.Serializable;

public class VeiculoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String modelo;
    private String placa;
    private String ano;
    private EstadoVeiculo estado;

    private VeiculoAlugado veiculoAlugado;

    public VeiculoDTO(){}

    public VeiculoDTO(Veiculo obj){
        id = obj.getId();
        modelo = obj.getModelo();
        placa = obj.getPlaca();
        ano = obj.getAno();
        estado = obj.getEstado();
        veiculoAlugado = obj.getVeiculoAlugado();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public EstadoVeiculo getEstado() {
        return estado;
    }

    public void setEstado(EstadoVeiculo estado) {
        this.estado = estado;
    }

    public VeiculoAlugado getVeiculoAlugado() {
        return veiculoAlugado;
    }

    public void setVeiculoAlugado(VeiculoAlugado veiculoAlugado) {
        this.veiculoAlugado = veiculoAlugado;
    }
}
