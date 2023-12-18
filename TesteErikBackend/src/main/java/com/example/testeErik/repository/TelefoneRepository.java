package com.example.testeErik.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.testeErik.domain.Telefone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Classe de Repositorio responsavel por manter o {@link Telefone}.
 */
@Component
public class TelefoneRepository {

	String baseUri = "http://jsonserver:5000/Clientes";

	/**
	 * Buscar {@link Telefone} por numero.
	 *
	 * @param numero , O número de telefone a ser buscado tipo {@link String}
	 * @return Optional do {@link Telefone} encontrado
	 */
	public Optional<Telefone> buscarPorNumero(@NotBlank String numero) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = String.format("%s%s%s", baseUri, "?numero=", numero);
		Telefone[] telefones = restTemplate.getForObject(uri, Telefone[].class);
		List<Telefone> lista = Arrays.asList(telefones);
		Telefone retorno = lista.isEmpty() ? null : lista.get(0);
		return Optional.ofNullable(retorno);
	}

	/**
	 * Salvar Entidade {@link Telefone}.
	 *
	 * @param telefone , O telefone a ser salvo tipo {@link Telefone}
	 * @return o {@link Telefone} salvo
	 */
	public Telefone salvarTelefone(@NotNull Telefone telefone) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(baseUri, telefone, Telefone.class);
	}
}
