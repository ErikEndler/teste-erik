package com.example.testeErik.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.testeErik.controllers.dtos.ClienteInput;
import com.example.testeErik.domain.Cliente;
import com.example.testeErik.domain.Telefone;
import com.example.testeErik.repository.ClienteRepository;
import com.example.testeErik.repository.TelefoneRepository;

@SpringBootTest
class ServicesTests {

	@Mock
	private TelefoneRepository telefoneRepository;

	@Mock
	private ClienteRepository clienteRepository;

	@InjectMocks
	private ClienteService clienteService;

	@Test
	void testSalvarTelefoneExistente() {
		List<String> telefones = Arrays.asList("(11)1111-1111");
		ClienteInput cliente = new ClienteInput("nome sobrenome", "endereco", "bairro", telefones);

		Telefone telefone = new Telefone("", "(11)1111-1111", "");

		when(telefoneRepository.buscarPorNumero(telefone.numero())).thenReturn(Optional.ofNullable(telefone));

		Exception exception = assertThrows(BadRequestException.class, () -> {
			clienteService.salvarCliente(cliente);
		});

		String expectedMessage = "número indisponível";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testSalvarTelefone() throws BadRequestException {
		List<String> telefones = Arrays.asList("(11)1111-1111");
		ClienteInput clienteInput = new ClienteInput("nome sobrenome", "endereco", "bairro", telefones);
		Cliente cliente = Cliente.criarCliente(clienteInput.nome(), clienteInput.endereco(), clienteInput.bairro());

		when(telefoneRepository.buscarPorNumero(telefones.get(0))).thenReturn(Optional.ofNullable(null));

		when(clienteRepository.salvarCliente(any(Cliente.class))).thenReturn(cliente);

		Cliente clienreResponse = clienteService.salvarCliente(clienteInput);

		assertEquals(clienreResponse, cliente);

	}

}
