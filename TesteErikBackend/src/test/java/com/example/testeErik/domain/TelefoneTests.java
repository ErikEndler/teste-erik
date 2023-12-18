package com.example.testeErik.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TelefoneTests {

	@Test
	void testTelefoneNumeroNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Telefone.criarTelefone(null, "uuid");
		});
		String expectedMessage = "número não pode ser nulo";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testTelefoneNumeroFormatoInvalido() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Telefone.criarTelefone("1234-1234", "uuid");
		});
		String expectedMessage = "número inválido, formato esperado (11)1111-1111";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testTelefoneNumeroFormatoValido() {
		Telefone telefone = Telefone.criarTelefone("(62)1234-1234", "uuid");
		assertEquals(telefone.numero(), "(62)1234-1234");
		assertEquals(telefone.clienteId(), "uuid");
		assertFalse(telefone.id().isEmpty());
	}
}
