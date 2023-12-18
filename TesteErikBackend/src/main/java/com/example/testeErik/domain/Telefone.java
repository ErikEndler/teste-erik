package com.example.testeErik.domain;

import java.util.Objects;

/**
 * Representa Entidade {@link Telefone}.
 */
public record Telefone(String id, String numero, String clienteId) {

	/**
	 * Instancia um novo telefone.
	 *
	 * @param id        o id do telefone
	 * @param numero    o nuemro do telefone
	 * @param clienteId o cliente id
	 */
	public Telefone {
		if (Objects.isNull(numero)) {
			throw new IllegalArgumentException("número não pode ser nulo");
		}
		if (!numero.matches("\\(\\d{2}\\)\\d{4}-\\d{4}")) {
			throw new IllegalArgumentException("número inválido, formato esperado (11)1111-1111");
		}
	}

	/**
	 * Cria {@link Telefone} atribuindo id ({@link java.util.UUID}) de forma
	 * automatica .
	 *
	 * @param numero    o numero do telefone {@link String}
	 * @param clienteId o id do cliente vinculado {@link String}
	 * @return um {@link Telefone} com id ({@link java.util.UUID}) gerado
	 *         automaticamente
	 */
	public static Telefone criarTelefone(String numero, String clienteId) {
		return new Telefone(IdGenerator.nextId(), numero, clienteId);
	}

}
