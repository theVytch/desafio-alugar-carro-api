package com.eduardo.alugarCarro.services;

import com.eduardo.alugarCarro.dto.VeiculoDTO;
import com.eduardo.alugarCarro.entities.Veiculo;
import com.eduardo.alugarCarro.repositories.VeiculoRepository;
import com.eduardo.alugarCarro.services.exceptions.DatabaseException;
import com.eduardo.alugarCarro.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Veiculo> findAll(){
        return veiculoRepository.findAll();
    }


    public List<Veiculo> findAllBy(){
        return veiculoRepository.findAll()
                .stream()
                .filter(veiculo -> Objects.isNull( veiculo.getVeiculoAlugado()))
                .collect(Collectors.toList());
    }


    public Veiculo findById(Long id){
        Optional<Veiculo> Veiculo = veiculoRepository.findById(id);
        return Veiculo.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Veiculo insert(Veiculo veiculo){
        return veiculoRepository.save(veiculo);
    }

    public void delete(Long id) {
        try {
            veiculoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Veiculo update(Veiculo VeiculoUpdate){
        try {
            Veiculo newVeiculo = findById(VeiculoUpdate.getId());
            updateDate(newVeiculo, VeiculoUpdate);
            return veiculoRepository.save(newVeiculo);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(VeiculoUpdate.getId());
        }
    }

    public void updateDate(Veiculo entity, Veiculo obj){
        entity.setModelo(obj.getModelo());
        entity.setPlaca(obj.getPlaca());
        entity.setAno(obj.getAno());
        entity.setEstado(obj.getEstado());
        entity.setVeiculoAlugado(obj.getVeiculoAlugado());
    }

    public Veiculo fromDTO(VeiculoDTO veiculoDTO){
        return new Veiculo(veiculoDTO.getId(), veiculoDTO.getModelo(), veiculoDTO.getPlaca(), veiculoDTO.getAno(), veiculoDTO.getEstado());
    }
}
