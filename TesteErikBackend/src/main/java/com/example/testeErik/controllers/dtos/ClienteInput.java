package com.example.testeErik.controllers.dtos;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * record que representa dados de input de criação de um cliente
 */
public record ClienteInput(@Size(min = 10) String nome, String endereco, String bairro,
		@NotNull List<@NotNull @Pattern(regexp = "\\(\\d{2}\\)\\d{4}-\\d{4}", message = "modelo valido (11)1111-1111") String> telefones) {

}
