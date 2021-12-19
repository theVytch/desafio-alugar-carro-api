package com.eduardo.alugarCarro.resources;

import com.eduardo.alugarCarro.dto.VeiculoDTO;
import com.eduardo.alugarCarro.entities.Veiculo;
import com.eduardo.alugarCarro.services.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/veiculos")
public class VeiculoResource {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> findAll(){
        List<Veiculo> veiculoList = veiculoService.findAll();
        List<VeiculoDTO> listaDto = veiculoList.stream().map(VeiculoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaDto);
    }

    @GetMapping("/veiculos")
    public ResponseEntity<List<Veiculo>> findAllBy(){
        List<Veiculo> veiculoList = veiculoService.findAllBy();
        return ResponseEntity.ok().body(veiculoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> findById(@PathVariable Long id){
        Veiculo veiculo = veiculoService.findById(id);
        return ResponseEntity.ok().body(new VeiculoDTO(veiculo));
    }

    @PostMapping
    public ResponseEntity<VeiculoDTO> insert(@RequestBody VeiculoDTO veiculoDTO){
        Veiculo veiculo = veiculoService.fromDTO(veiculoDTO);
        veiculo = veiculoService.insert(veiculo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(veiculo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        veiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDTO> update(@PathVariable Long id, @RequestBody VeiculoDTO veiculoDTO){
        Veiculo veiculo = veiculoService.fromDTO(veiculoDTO);
        veiculo.setId(id);
        veiculo = veiculoService.update(veiculo);
        return ResponseEntity.noContent().build();
    }
}
