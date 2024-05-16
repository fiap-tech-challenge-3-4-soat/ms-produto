package br.com.tech.challenge.msproduto.core.domain.vo;

import jakarta.validation.constraints.NotBlank;

public class Descricao extends ValueObjectValidated {
    @NotBlank
    private final String valor;

    public Descricao(String valor) {
        this.valor = valor;
        this.validar();
    }

    @Override
    public String toString() {
        return valor;
    }
}
