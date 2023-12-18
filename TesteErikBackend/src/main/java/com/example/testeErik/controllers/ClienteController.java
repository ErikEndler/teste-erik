package com.example.testeErik.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.testeErik.controllers.dtos.ClienteInput;
import com.example.testeErik.domain.Telefone;
import com.example.testeErik.repository.TelefoneRepository;
import com.example.testeErik.services.ClienteService;

import jakarta.validation.Valid;

/**
 * Classe resposavel pelos endpoints referentes ao cliente.
 */
@RestController
@RequestMapping(value = "clientes")
public class ClienteController {

	@Autowired
	TelefoneRepository telefoneRepository;

	@Autowired
	ClienteService clienteService;

	/**
	 * Endpoint de criação do cliente.
	 *
	 * @param clienteInput o cliente a ser criado tipo {@link ClienteInput}
	 * @return {@link ResponseEntity} informando que opereção foi bem sucedida
	 * @throws BadRequestException the bad request exception
	 */
	@PostMapping
	public ResponseEntity<String> criarCliente(@RequestBody @Valid ClienteInput clienteInput) throws BadRequestException {
		clienteService.salvarCliente(clienteInput);
		return new ResponseEntity<>("Cliente Criado", HttpStatus.CREATED);
	}

	/**
	 * Endpoint de busca do telefone.
	 *
	 * @param telefone número do telefone a ser buscado
	 * @return o {@link Telefone} correspondente ao número passado
	 */
	@GetMapping("/{telefone}")
	public Boolean buscarTelefone(@PathVariable String telefone) {
		return clienteService.validarTelefone(telefone);

	}

}
