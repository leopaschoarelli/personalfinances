package br.com.personal.finances.core.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    private String username;
    private String password;

    // getters e setters
}