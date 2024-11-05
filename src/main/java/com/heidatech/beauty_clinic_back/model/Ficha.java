package com.heidatech.beauty_clinic_back.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Ficha {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "tipo_ficha_id")
    private TipoFicha tipoFicha;

    private Date data_cadastro;
}
