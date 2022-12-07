package br.com.criandoapi.projeto.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.criandoapi.projeto.model.Usuario;
import br.com.criandoapi.projeto.repository.IUsuario;

@Service
public class UsuarioService {

    private IUsuario repository;
    private PasswordEncoder passwordEncoder;

    public UsuarioService (IUsuario repository){ // Criar um construtor pode ser uma alternativa para substituir a anotations autowired
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Usuario> listarUsuarios(){
        List<Usuario> lista = repository.findAll();
        return lista;
    }

    public Usuario criarUsuario(Usuario usuario){
        String encoder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encoder);
        Usuario usuarioNovo = repository.save(usuario);
        return usuarioNovo;
    }

    public Usuario editarUsuario(Usuario usuario){
        String encoder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encoder);
        Usuario usuarioEditado = repository.save(usuario);
        return usuarioEditado;
    }

    public Boolean excluirUsuario(Integer id){
        
        try{
            repository.deleteById(id);
            return true;
        }catch (Exception error){
            System.out.println("Não foi possível encontrar o usuário com o id "+ id );
            System.out.println("Erro capturado: " + error);
            return false;
        }
        
    }

    public Boolean validarSenha(Usuario usuario) {
        String senha = repository.getReferenceById(usuario.getId()).getSenha(); // Vai pegar o usuario pelo Id através do repository (que ecnriptou a senha) e então vai pegar a senha do usuario
        Boolean isValid = passwordEncoder.matches(usuario.getSenha(), senha);// Esse método vai comparar a senha do objeto usuário com a senha encriptada
        return isValid;
    }

}
