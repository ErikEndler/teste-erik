package com.example.testeErik.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.testeErik.domain.Cliente;

import jakarta.validation.constraints.NotNull;

/**
 * Classe de Repositorio responsavel por manter o {@link Cliente}.
 */
@Repository
public class ClienteRepository {

	String baseUri = "http://jsonserver:5000/Clientes";

	/**
	 * Salvar Entidade {@link Cliente} .
	 *
	 * @param cliente , O cliente a ser salvo tipo {@link Cliente}
	 * @return o {@link Cliente} salvo
	 */
	public Cliente salvarCliente(@NotNull Cliente cliente) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(baseUri, cliente, Cliente.class);
	}

}
