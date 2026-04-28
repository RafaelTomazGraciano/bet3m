package com.bet3m.betUenp.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LutasRepository extends JpaRepository <LutasEntity, Integer> {
}
