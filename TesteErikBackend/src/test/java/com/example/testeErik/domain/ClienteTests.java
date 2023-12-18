package com.example.testeErik.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteTests {

	@Test
	void testClienteNomeInvalido() {

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Cliente.criarCliente("nome", "", "");
		});

		String expectedMessage = "O nome nao pode ser menor que 10 caracteres";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testClienteNomeValido() {
		Cliente cliente = Cliente.criarCliente("nome sobrenome", "", "");
		assertFalse(cliente.nome().isEmpty());
	}

	@Test
	void testCriarCliente() {
		Cliente cliente = Cliente.criarCliente("nome sobrenome", "endereco", "bairro");
		assertEquals(cliente.bairro(), "bairro");
		assertEquals(cliente.endereco(), "endereco");
		assertEquals(cliente.nome(), "nome sobrenome");
		assertFalse(cliente.id().isEmpty());
	}

}
