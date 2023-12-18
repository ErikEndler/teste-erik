package com.example.testeErik.controllers.dtos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@SpringBootTest
class ClienteInputTests {

	@Autowired
	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void testClienteSuccess() {
		List<String> telefones = Arrays.asList("(11)1111-1111");

		ClienteInput cliente = new ClienteInput("nome sobrenome", "endereco", "bairro", telefones);

		Set<ConstraintViolation<ClienteInput>> violations = validator.validate(cliente);
		System.err.println(violations);
		assertTrue(violations.isEmpty());
	}

	@Test
	void testTelefoneNull() {
		List<String> telefones = null;

		ClienteInput cliente = new ClienteInput("nome sobrenome", "endereco", "bairro", telefones);

		Set<ConstraintViolation<ClienteInput>> violations = validator.validate(cliente);
		assertFalse(violations.isEmpty());
	}

	@Test
	void testTelefoneInvalido() {
		List<String> telefones = Arrays.asList("(11)91111-1111");

		ClienteInput cliente = new ClienteInput("nome sobrenome", "endereco", "bairro", telefones);

		Set<ConstraintViolation<ClienteInput>> violations = validator.validate(cliente);
		assertFalse(violations.isEmpty());
	}

	@Test
	void testNomeInvalido() {
		List<String> telefones = Arrays.asList("(11)1111-1111");

		ClienteInput cliente = new ClienteInput("nome", "endereco", "bairro", telefones);

		Set<ConstraintViolation<ClienteInput>> violations = validator.validate(cliente);
		assertFalse(violations.isEmpty());
	}
}
