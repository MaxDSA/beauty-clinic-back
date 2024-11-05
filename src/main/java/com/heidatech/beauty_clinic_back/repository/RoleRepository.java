package com.heidatech.beauty_clinic_back.repository;

import com.heidatech.beauty_clinic_back.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByNome(String nome);
}
