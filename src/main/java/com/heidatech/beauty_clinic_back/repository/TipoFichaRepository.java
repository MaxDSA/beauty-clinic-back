package com.heidatech.beauty_clinic_back.repository;

import com.heidatech.beauty_clinic_back.model.TipoFicha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoFichaRepository extends JpaRepository<TipoFicha, Long> {
}
