package com.example.testeErik.services;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.testeErik.controllers.dtos.ClienteInput;
import com.example.testeErik.domain.Cliente;
import com.example.testeErik.domain.Telefone;
import com.example.testeErik.repository.ClienteRepository;
import com.example.testeErik.repository.TelefoneRepository;

/**
 * Classe de serviço responsável por manipular dados do cliente
 */
@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	TelefoneRepository telefoneRepository;

	/**
	 * Salvar cliente.
	 *
	 * @param clienteInput dados do cliente a ser salvo
	 * @return o cliente salvo
	 * @throws BadRequestException the bad request exception
	 */
	public Cliente salvarCliente(ClienteInput clienteInput) throws BadRequestException {
		validarTelefones(clienteInput.telefones());
		Cliente cliente = Cliente.criarCliente(clienteInput.nome(), clienteInput.endereco(), clienteInput.bairro());
		Cliente clienteSalvo = clienteRepository.salvarCliente(cliente);
		salvarTelefone(clienteInput.telefones(), clienteSalvo);
		return clienteSalvo;
	}

	/**
	 * Salva telefone vinculado a um cliente
	 *
	 * @param telefones lista dos telefones que serão salvos e vinculados ao cliente
	 * @param cliente   o cliente o qual o telefone é vinculado
	 */
	public void salvarTelefone(List<String> telefones, Cliente cliente) {
		for (String numero : telefones) {
			telefoneRepository.salvarTelefone(Telefone.criarTelefone(numero, cliente.id()));
		}
	}

	/**
	 * Faz validação de um telefone.
	 *
	 * @param telefone o telefone a ser validado
	 * @return um {@link Boolean} informando se o telefone esta válido
	 */
	public Boolean validarTelefone(String telefone) {
		return telefoneRepository.buscarPorNumero(telefone).isEmpty();
	}

	/**
	 * Faz validaçao de uma lista de telefones
	 *
	 * @param telefones lista de telefones a serem validados
	 * @throws BadRequestException the bad request exception
	 */
	private void validarTelefones(List<String> telefones) throws BadRequestException {
		for (String telefone : telefones) {
			if (telefoneRepository.buscarPorNumero(telefone).isPresent()) {
				throw new BadRequestException("número indisponível");
			}
		}
	}

}
