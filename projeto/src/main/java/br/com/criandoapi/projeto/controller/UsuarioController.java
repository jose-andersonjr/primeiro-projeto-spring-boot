package br.com.criandoapi.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.criandoapi.projeto.DAO.IUsuario;

@RestController
public class UsuarioController{
	
	@Autowired
	private IUsuario dao;
	
	@GetMapping("/usuarios")
	public String texto () {
		
		return "Hello World";
	}

}
