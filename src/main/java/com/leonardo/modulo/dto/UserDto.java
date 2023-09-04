package com.leonardo.modulo.dto;

public record UserDto(Long id, String name, String username, String password, String cep) {
    @Override
    public Long id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public String cep() {
        return cep;
    }
}
