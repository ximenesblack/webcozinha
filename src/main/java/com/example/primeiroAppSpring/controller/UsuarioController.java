package com.example.primeiroAppSpring.controller;

import com.example.primeiroAppSpring.model.UsuarioForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UsuarioController {

    // --- CADASTRO ---
    @GetMapping("/cadastro")
    public String exibirCadastro(Model form){
        form.addAttribute("UsuarioForm", new UsuarioForm());
        form.addAttribute("TituloPagina", "Cadastro");
        form.addAttribute("SubTituloPagina", "Sistema de Gerenciamento de Estoque");
        return "cadastro";
    }

    // AQUI: Processa o envio do formulário de cadastro
    @PostMapping("/cadastro")
    public String processarCadastro(@ModelAttribute UsuarioForm form ,Model model ) {
        if(!form.getSenha().equals(form.getConfirmarSenha())){
            model.addAttribute("erro", "Erro, as senhas não conferem");
            return "cadastro";
        }
        // Aqui dentro você faria a lógica para salvar no banco de dados futuramente
        IO.println("Usuário cadastrado: " + form.getNome()); // Exemplo de teste

        return "redirect:/Login"; // Redireciona para a página de login após cadastrar
    }

    // --- LOGIN ---
    @GetMapping("/login")
    public String exibirLogin(Model form){
        form.addAttribute("UsuarioForm", new UsuarioForm());
        form.addAttribute("TituloPagina", "Bem-vindo");
        form.addAttribute("SubTituloPagina", "Sistema de Gerenciamento de Estoque de Cozinha");
        return "login";
    }

    // AQUI: Processa a tentativa de login (Mudei de Get para Post)
    @PostMapping("/login")
    public String processarLogin(@ModelAttribute UsuarioForm form , Model model) {
        if(form.getEmail().endsWith("@df.senac.br") || form.getEmail().endsWith("@edu.df.senac.br")){
            return "redirect:/home";
        }
        model.addAttribute("erro","Email ou senha invalidos ");

        return "login"; // Exemplo: manda para o cadastro se der certo
    }

    // --- ALTERAR SENHA ---
    @GetMapping("/AlterarSenha")
    public String exibirAlterarSenha(Model form){
        form.addAttribute("UsuarioForm", new UsuarioForm());
        form.addAttribute("TituloPagina", "Alterar Senha");
        form.addAttribute("SubTituloPagina", "Informe seus dados para redefinir sua senha.");
        return "alterarSenha";
    }

    // AQUI: Processa a alteração de senha
    @PostMapping("/AlterarSenha")
    public String processarAlrterarSenha(@ModelAttribute UsuarioForm form ,Model model ) {
        if(!form.getSenha().equals(form.getConfirmarSenha())){
            model.addAttribute("erro", "Erro, as senhas não conferem");
            return "AlterarSenha";
        }
        // Aqui dentro você faria a lógica para salvar no banco de dados futuramente
        model.addAttribute("Senha alterada com sucesso: " + form.getEmail()); // Exemplo de teste

        return "redirect:/login"; // Redireciona para a página de login após cadastrar
    }
}