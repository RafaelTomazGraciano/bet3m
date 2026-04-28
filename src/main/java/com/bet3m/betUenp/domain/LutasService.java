package com.bet3m.betUenp.domain;

import com.bet3m.betUenp.exception.BadRequestException;
import com.bet3m.betUenp.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LutasService {

    private final LutasRepository lutasRepository;

    @Transactional
    public LutasEntity criarLuta(LutasEntity luta){
        validarLutadoresDiferentes(luta);
        return lutasRepository.save(luta);
    }

    @Transactional(readOnly = true)
    public List<LutasEntity> listarLutas(){
        return lutasRepository.findAll();
    }

    @Transactional(readOnly = true)
    public LutasEntity buscaLuta(Integer id){
        return lutasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Luta não encontrada para o id: " + id));
    }

    @Transactional
    public LutasEntity atualizaLutas(Integer id,LutasEntity luta){
        lutasRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Luta não encontrada para o id: " + id));

        validarLutadoresDiferentes(luta);
        luta.setId(id);
        return lutasRepository.save(luta);
    }

    @Transactional
    public void deletar(Integer id){
        lutasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Luta não encontrada para o id: " + id));
        lutasRepository.deleteById(id);
    }

    private void validarLutadoresDiferentes(LutasEntity luta) {
        if (luta.getLutador1().equals(luta.getLutador2())) {
            throw new BadRequestException("Lutador 1 e Lutador 2 não podem ser o mesmo");
        }
    }
}

