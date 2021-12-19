package com.eduardo.alugarCarro.resources;

import com.eduardo.alugarCarro.dto.VeiculoAlugadoDTO;
import com.eduardo.alugarCarro.dto.VeiculoDTO;
import com.eduardo.alugarCarro.entities.VeiculoAlugado;
import com.eduardo.alugarCarro.services.VeiculoAlugadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/veiculosAlugados")
public class VeiculoAlugadoResource {

    @Autowired
    private VeiculoAlugadoService veiculoAlugadoService;

    @GetMapping
    public ResponseEntity<List<VeiculoAlugadoDTO>> findAll(){
        List<VeiculoAlugado> veiculoAlugadoList = veiculoAlugadoService.findAll();
        List<VeiculoAlugadoDTO> listaDto = veiculoAlugadoList.stream().map(x -> new VeiculoAlugadoDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoAlugadoDTO> findById(@PathVariable Long id){
        VeiculoAlugado veiculoAlugado = veiculoAlugadoService.findById(id);
        return ResponseEntity.ok().body(new VeiculoAlugadoDTO(veiculoAlugado));
    }

    @PostMapping
    public ResponseEntity<VeiculoDTO> insert(@RequestBody VeiculoAlugadoDTO veiculoAlugadoDTO){
        VeiculoAlugado veiculoAlugado = veiculoAlugadoService.fromDTO(veiculoAlugadoDTO);
        veiculoAlugado = veiculoAlugadoService.insert(veiculoAlugado);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(veiculoAlugado.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        veiculoAlugadoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDTO> update(@PathVariable Long id, @RequestBody VeiculoAlugadoDTO veiculoAlugadoDTO){
        VeiculoAlugado veiculoAlugado = veiculoAlugadoService.fromDTO(veiculoAlugadoDTO);
        veiculoAlugado.setId(id);
        veiculoAlugado = veiculoAlugadoService.update(veiculoAlugado);
        return ResponseEntity.noContent().build();
    }
}
