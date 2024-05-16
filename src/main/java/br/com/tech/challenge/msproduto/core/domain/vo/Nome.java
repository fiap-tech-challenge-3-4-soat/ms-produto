package br.com.tech.challenge.msproduto.core.domain.vo;

import jakarta.validation.constraints.NotBlank;

public class Nome extends ValueObjectValidated {
    @NotBlank
    private final String name;

    public Nome(String name) {
        this.name = name;
        this.validar();
    }

    @Override
    public String toString() {
        return name;
    }
}
