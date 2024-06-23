package br.com.personal.finances.api.exception;

import lombok.Getter;

@Getter
public enum ProblemType {

    ACCESS_DENIED("/access-denied", "Access denied");

    private final String title;
    private final String uri;

    ProblemType(String path, String title) {
        this.uri = "https://localhost:8008" + path;
        this.title = title;
    }

}