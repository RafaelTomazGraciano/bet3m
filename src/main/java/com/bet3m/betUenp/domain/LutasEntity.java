package com.bet3m.betUenp.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LutasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Horário é obrigatório")
    private LocalTime horario;

    @NotNull(message = "Data é obrigatória")
    private LocalDate data;

    @NotNull(message = "ID do lutador 1 é obrigatório")
    private Integer lutador1;

    @NotNull(message = "ID do lutador 2 é obrigatório")
    private Integer lutador2;

}
