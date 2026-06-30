package com.example.primeiroAppSpring.service;

import com.example.primeiroAppSpring.model.Usuario;
import com.example.primeiroAppSpring.model.UsuarioForm;
import com.example.primeiroAppSpring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String cadastrar(UsuarioForm form){
        if(!form.getSenha().equals(form.getConfirmarSenha())){
            return "As senhas não conferem";
        }
        IO.println(form.getEmail()+"esse é o email");
        if(usuarioRepository.existsByEmail(form.getEmail())){
            return "E-mail já cadastrado no banco";
        }

        String senhaCriptografada = encoder.encode(form.getSenha());

        Usuario novoUsuario = new Usuario(form.getNome(), form.getEmail(), senhaCriptografada);

        usuarioRepository.save(novoUsuario);

        return null;
    }
    public Usuario autenticar(String email, String senha){
        Optional<Usuario> resultado = usuarioRepository.findByEmail(email);
        if(resultado.isEmpty()) {
            return null;
        }
        Usuario usuario = resultado.get();
        
    }
}