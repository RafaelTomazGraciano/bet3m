package com.bet3m.betUenp.domain;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lutas")
public class LutasController {

    private final LutasService lutasService;

    @PostMapping
    public ResponseEntity<LutasEntity> criarLuta(@Valid @RequestBody LutasEntity lutaEntity){
        LutasEntity luta = lutasService.criarLuta(lutaEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(luta);
    }

    @GetMapping
    public ResponseEntity<List<LutasEntity>> listarLutas(){
        return ResponseEntity.ok(lutasService.listarLutas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LutasEntity> buscaLuta(@PathVariable Integer id){
        return ResponseEntity.ok(lutasService.buscaLuta(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LutasEntity> atualizaLutas(@PathVariable Integer id, @Valid @RequestBody LutasEntity luta){
        return ResponseEntity.ok(lutasService.atualizaLutas(id, luta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaLuta(@PathVariable Integer id){
        lutasService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
