package com.eduardo.alugarCarro.entities;

import com.eduardo.alugarCarro.entities.enums.EstadoVeiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_veiculo")
public class Veiculo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="jpaPkSeq", sequenceName="JPA_PK_SEQ", allocationSize=1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaPkSeq")
    @Column(name="id", nullable=false, updatable=false)
    private Long id;
    private String modelo;
    private String placa;
    private String ano;
    private EstadoVeiculo estado;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "veiculo", orphanRemoval = true)
    @JsonIgnore
    private VeiculoAlugado veiculoAlugado;

    protected Veiculo(){}

    public Veiculo(Long id, String modelo, String placa, String ano, EstadoVeiculo estado) {
        this.id = id;
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
        this.estado = estado;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return id.equals(veiculo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
