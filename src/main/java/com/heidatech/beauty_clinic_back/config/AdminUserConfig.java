package com.heidatech.beauty_clinic_back.config;

import com.heidatech.beauty_clinic_back.model.Usuario;
import com.heidatech.beauty_clinic_back.repository.RoleRepository;
import com.heidatech.beauty_clinic_back.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UsuarioRepository usuarioRepository;


    public AdminUserConfig(RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UsuarioRepository usuarioRepository) {
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception{
        var roleAdmin = roleRepository.findByNome("ADMIN");

        var userAdmin = usuarioRepository.findByLogin("JUDUARTE");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("Admin já existe");
                },
                () -> {
                    var user = new Usuario();
                    user.setLogin("JUDUARTE");
                    user.setSenha(bCryptPasswordEncoder.encode("123456"));
                    user.setNome("Juliana Duarte");
                    user.setCpf("60373280300");
                    user.setRoles(Set.of(roleAdmin));
                    usuarioRepository.save(user);
                }
        );
    }
}
