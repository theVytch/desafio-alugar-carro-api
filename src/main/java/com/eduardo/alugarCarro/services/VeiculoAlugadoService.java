package com.eduardo.alugarCarro.services;

import com.eduardo.alugarCarro.dto.VeiculoAlugadoDTO;
import com.eduardo.alugarCarro.entities.VeiculoAlugado;
import com.eduardo.alugarCarro.repositories.VeiculoAlugadoRepository;
import com.eduardo.alugarCarro.repositories.VeiculoRepository;
import com.eduardo.alugarCarro.services.exceptions.DatabaseException;
import com.eduardo.alugarCarro.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoAlugadoService {

    @Autowired
    private VeiculoAlugadoRepository veiculoAlugadoRepository;
    @Autowired
    private VeiculoRepository veiculoRepository;


    public List<VeiculoAlugado> findAll(){
        return veiculoAlugadoRepository.findAll();
    }

    public VeiculoAlugado findById(Long id){
        Optional<VeiculoAlugado> veiculoAlugado = veiculoAlugadoRepository.findById(id);
        return veiculoAlugado.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public VeiculoAlugado insert(VeiculoAlugado veiculoAlugado){
        return veiculoAlugadoRepository.save(veiculoAlugado);
    }

    public void delete(Long id) {
        try {
            veiculoAlugadoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public VeiculoAlugado update(VeiculoAlugado veiculoAlugadoUpdate){
        try {
            VeiculoAlugado newVeiculoAlugado = findById(veiculoAlugadoUpdate.getId());
            updateDate(newVeiculoAlugado, veiculoAlugadoUpdate);
            return veiculoAlugadoRepository.save(newVeiculoAlugado);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(veiculoAlugadoUpdate.getId());
        }
    }

    public void updateDate(VeiculoAlugado entity, VeiculoAlugado obj){
        entity.setInicioReserva(obj.getInicioReserva());
        entity.setFimReserva(obj.getFimReserva());
        entity.setEstado(obj.getEstado());
        entity.setVeiculo(obj.getVeiculo());
    }

    public VeiculoAlugado fromDTO(VeiculoAlugadoDTO veiculoAlugadoDTO){
        return new VeiculoAlugado(veiculoAlugadoDTO.getId(), veiculoAlugadoDTO.getInicioReserva(), veiculoAlugadoDTO.getFimReserva(), veiculoAlugadoDTO.getEstado(), veiculoAlugadoDTO.getVeiculo());
    }
}
