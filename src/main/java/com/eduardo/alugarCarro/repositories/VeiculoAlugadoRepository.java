package com.eduardo.alugarCarro.repositories;

import com.eduardo.alugarCarro.entities.VeiculoAlugado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface VeiculoAlugadoRepository extends JpaRepository<VeiculoAlugado, Long> {


    @Override
    @Transactional
    @Modifying
    @Query("delete from VeiculoAlugado where id = ?1")
    void deleteById(Long id);
}
