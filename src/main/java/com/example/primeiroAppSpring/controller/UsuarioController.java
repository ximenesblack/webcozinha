package com.example.primeiroAppSpring.controller;

import com.example.primeiroAppSpring.model.Usuario;
import com.example.primeiroAppSpring.model.UsuarioForm;
import com.example.primeiroAppSpring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UsuarioController {
    @Autowired
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }


    // --- CADASTRO ---
    @GetMapping("/cadastro")
    public String exibirCadastro(Model form){
        form.addAttribute("UsuarioForm", new UsuarioForm());
        form.addAttribute("tituloPagina", "Cadastro");
        form.addAttribute("subTituloPagina", "Sistema de Gerenciamento de Estoque");
        return "cadastro";
    }

    // AQUI: Processa o envio do formulário de cadastro
    @PostMapping("/cadastro")
    public String processarCadastro(@ModelAttribute UsuarioForm form ,Model model ) {
       String erro = usuarioService.cadastrar(form);
       if(erro != null){
           model.addAttribute("erro",erro);
           model.addAttribute("usuarioForm",form);
           return "cadastro";
       }
        return "redirect:/login"; // Redireciona para a página de login após cadastrar
    }

    // --- LOGIN ---
    @GetMapping("/login")
    public String exibirLogin(Model form){
        form.addAttribute("UsuarioForm", new UsuarioForm());
        form.addAttribute("tituloPagina", "Bem-vindo");
        form.addAttribute("subTituloPagina", "Sistema de Gerenciamento de Estoque de Cozinha");
        return "login";
    }

    // AQUI: Processa a tentativa de login (Mudei de Get para Post)
    @PostMapping("/login")
    public String processarLogin(@ModelAttribute UsuarioForm form , Model model) {
        Usuario usuario = usuarioService.autenticar(form.getEmail(), form.getSenha());
        if(usuario == null){
            model.addAttribute("erro","E-mail ou senha incorreto");
            return "login";
        }
        return "redirect:/home"; // Exemplo: manda para o cadastro se der certo
    }

    // --- ALTERAR SENHA ---
    @GetMapping("/alterarSenha")
    public String exibirAlterarSenha(Model form){
        form.addAttribute("UsuarioForm", new UsuarioForm());
        form.addAttribute("tituloPagina", "Alterar Senha");
        form.addAttribute("subTituloPagina", "Informe seus dados para redefinir sua senha.");
        return "alterarSenha";
    }

    // AQUI: Processa a alteração de senha
    @PostMapping("/alterarSenha")
    public String processarAlterarSenha(@ModelAttribute UsuarioForm form, Model model) {
        String erro = usuarioService.alterarSenha(form);

        if (erro != null) {
            model.addAttribute("erro", "Senhas não conferem ou E-mail não cadastrado");
            return "alterarSenha";

        }// Exemplo de teste

        return "redirect:/login"; // Redireciona para a página de login após cadastrar
    }
}