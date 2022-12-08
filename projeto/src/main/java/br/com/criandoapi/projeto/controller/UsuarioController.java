package br.com.criandoapi.projeto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.criandoapi.projeto.model.Usuario;
import br.com.criandoapi.projeto.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController{
	
	@Autowired //Auto-injeção de dependências

	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService){
		this.usuarioService = usuarioService;
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		return ResponseEntity.status(200).body(usuarioService.listarUsuarios());
	}
	
	@PostMapping
	public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.status(201).body(usuarioService.criarUsuario(usuario));
	}
	
	@PutMapping
	public ResponseEntity<Usuario> editarUsuario(@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.status(200).body(usuarioService.editarUsuario(usuario));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarUsuario(@PathVariable Integer id) { // <?> indica que eu não sei que tipo de dado vou receber, portando uso um genérico
		usuarioService.excluirUsuario(id);
		return ResponseEntity.status(204).build(); //build indica que ele não tem corpo
	}
	
	@PostMapping("/login")
	public ResponseEntity<Usuario> validarSenha(@Valid @RequestBody Usuario usuario){
		Boolean isValid = usuarioService.validarSenha(usuario);
		if(!isValid){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		System.out.println("Você foi logado!!");
		return ResponseEntity.status(200).build();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class) //Pegar a classe do Erro
	public Map<String, String> handleValidationException(MethodArgumentNotValidException except){
		Map<String, String> errors = new HashMap<>();

		except.getBindingResult().getAllErrors().forEach((error) -> { //Capturamos todos os erros da exceção
			String fieldName = ((FieldError) error).getField(); // Nome do campo do erro
			String errorMessage = error.getDefaultMessage(); // Mensagem de erro
			errors.put(fieldName, errorMessage); // Adiciona no Map erros sendo chave: fieldName e valor: errorMessage
		});

		return errors;
	}

}
