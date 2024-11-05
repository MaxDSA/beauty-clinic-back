package com.heidatech.beauty_clinic_back.controller;

import com.heidatech.beauty_clinic_back.model.Cliente;
import com.heidatech.beauty_clinic_back.repository.ClienteRepository;
import com.heidatech.beauty_clinic_back.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Cliente> listar(JwtAuthenticationToken token) {
        var user = usuarioRepository.findById(Long.valueOf((token.getName())));

        return clienteRepository.findAllByUsuario(user);
    };

    @GetMapping("/{id}")
    public Cliente buscar(@PathVariable Long id) throws Exception {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            return cliente.get();
        }else{
            throw new Exception("Cliente não encontrado");
        }
    }

    @PostMapping
    public Cliente criar(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @PutMapping
    public Cliente atualizar(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @DeleteMapping
    public void remover(@RequestBody Cliente cliente){
        clienteRepository.delete(cliente);
    }
}
