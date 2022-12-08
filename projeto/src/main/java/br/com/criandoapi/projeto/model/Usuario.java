package br.com.criandoapi.projeto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "usuario")
@Data //Substitui a criação dos métodos getters, setters, equalsAndHashCode e toString
@AllArgsConstructor // Criar automaticamente os construtores com todos os argumentos necessários
@NoArgsConstructor // Criar automaticamente os construtores sem argumentos
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotBlank(message = "O nome é obrigatório")
	@Size(min = 3, message = "O nome deve possuir no mínimo 3 caracteres")
	@Column(name = "nome_completo", length = 200, nullable = false)
	private String nome;
	
	@NotBlank(message = "O username é obrigatório")
	@Column(name = "username", length = 200, nullable = false)
	private String username;
	
	@Email(message = "Insira um email válido")
	@NotBlank(message = "O email é obrigatório")
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	
	@NotBlank(message = "A senha é obrigatória")
	@Column(name = "senha", columnDefinition = "TEXT", nullable = false)
	private String senha;
	
	@NotBlank(message = "O telefone é obrigatório")
	@Column(name = "telefone", length = 15, nullable = false)
	private String telefone;
	
	
}
