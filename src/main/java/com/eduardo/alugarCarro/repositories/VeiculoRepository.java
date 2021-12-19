package com.eduardo.alugarCarro.repositories;

import com.eduardo.alugarCarro.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
