package br.com.personal.finances.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

    @interface Test {

        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('READ_TEST1')")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface readTest1 { }

        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('READ_TEST2')")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface readTest2 { }

        @PreAuthorize("isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface readTest3 { }

    }

}
