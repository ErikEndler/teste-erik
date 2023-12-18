package com.example.testeErik.domain;

import java.util.UUID;

/**
 * The Class IdGenerator.
 */
public final class IdGenerator {

	/**
	 * Gera um randomUUID.
	 *
	 * @return um UUID({@link java.util.UUID}) randômico tipo {@link String}
	 */
	public static String nextId() {
		return UUID.randomUUID().toString();
	}

}
