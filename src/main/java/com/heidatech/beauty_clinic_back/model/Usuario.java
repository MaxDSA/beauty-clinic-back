package com.heidatech.beauty_clinic_back.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.heidatech.beauty_clinic_back.model.dto.LoginRequest;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    private String login;

    @JsonIgnore
    private String senha;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "role_id")
    private Set<Role> roles;

    public boolean isLoginValido(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequest.senha(), this.senha);
    }
}
