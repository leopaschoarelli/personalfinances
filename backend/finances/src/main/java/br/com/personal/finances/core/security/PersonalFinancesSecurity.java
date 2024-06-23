package br.com.personal.finances.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class PersonalFinancesSecurity {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUserId() {
        var jwt = (Jwt) getAuthentication().getPrincipal();

        Object userId = jwt.getClaim("user_id");

        if (userId == null) {
            return null;
        }

        return Long.valueOf(userId.toString());
    }

    public boolean hasAuthority(String authorityName) {

        return getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(authorityName));
    }

}