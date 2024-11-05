package com.heidatech.beauty_clinic_back.controller;

import com.heidatech.beauty_clinic_back.model.Usuario;
import com.heidatech.beauty_clinic_back.model.dto.CreateUserDto;
import com.heidatech.beauty_clinic_back.repository.RoleRepository;
import com.heidatech.beauty_clinic_back.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
public class UsuarioController {

    private UsuarioRepository usuarioRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    @PostMapping(value = "/usuario")
    public ResponseEntity<Void> newUser(@RequestBody CreateUserDto createUserDto){
        var basicRole = roleRepository.findByNome("BASIC");

        var userFromDb = usuarioRepository.findByLogin(createUserDto.login());

        if(userFromDb.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = new Usuario();
        user.setNome(createUserDto.nome());
        user.setLogin(createUserDto.login());
        user.setSenha(passwordEncoder.encode(createUserDto.senha()));
        user.setRoles(Set.of(basicRole));
        user.setCpf(createUserDto.cpf());
        usuarioRepository.save(user);

        return ResponseEntity.ok().build();
    };

    @GetMapping("/usuarios")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.ok(usuarioRepository.findAll());
    }
}
