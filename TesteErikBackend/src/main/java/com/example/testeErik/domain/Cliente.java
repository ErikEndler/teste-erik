package com.example.testeErik.domain;

/**
 * Representa Entidade {@link Cliente}.
 */
public record Cliente(String id, String nome, String endereco, String bairro) {

	public Cliente {
		if (nome.length() < 10) {
			throw new IllegalArgumentException("O nome nao pode ser menor que 10 caracteres");
		}
	}

	/**
	 * Instância {@link Cliente} atribuindo id ({@link java.util.UUID}) de forma
	 * automatica .
	 *
	 * @param nome     o nome do cliente {@link String}
	 * @param endereco o endereco do cliente {@link String}
	 * @param bairro   o bairro do cliente {@link String}
	 * @return um {@link Cliente} com id ({@link java.util.UUID}) gerado
	 *         automaticamente
	 */
	public static Cliente criarCliente(String nome, String endereco, String bairro) {
		return new Cliente(IdGenerator.nextId(), nome, endereco, bairro);
	}

}
