package com.heidatech.beauty_clinic_back.repository;

import com.heidatech.beauty_clinic_back.model.Cliente;
import com.heidatech.beauty_clinic_back.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    List<Cliente> findAllByUsuario(Optional<Usuario> user);
}
