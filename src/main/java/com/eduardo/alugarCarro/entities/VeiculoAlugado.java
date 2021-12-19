package com.eduardo.alugarCarro.entities;

import com.eduardo.alugarCarro.entities.enums.EstadoVeiculo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_veiculo_alugado")
public class VeiculoAlugado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="jpaPkSeq", sequenceName="JPA_PK_SEQ", allocationSize=1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaPkSeq")
    @Column(name="id", nullable=false, updatable=false)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Date inicioReserva;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Date fimReserva;
    private EstadoVeiculo estado;

    @OneToOne
    @JoinColumn(name = "veiculo_id", referencedColumnName = "id")
    @JsonIgnore
    private Veiculo veiculo;

    public VeiculoAlugado(){}

    public VeiculoAlugado(Long id, Date inicioReserva, Date fimReserva, EstadoVeiculo estado, Veiculo veiculo) {
        this.id = id;
        this.inicioReserva = inicioReserva;
        this.fimReserva = fimReserva;
        this.estado = estado;
        this.veiculo = veiculo;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VeiculoAlugado that = (VeiculoAlugado) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
