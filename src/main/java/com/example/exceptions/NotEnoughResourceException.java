package com.example.exceptions;

import lombok.Getter;

public class NotEnoughResourceException extends Exception {

    @Getter
    private String resourceType;

    public NotEnoughResourceException(String message, String resourceType) {
        super(message);
        this.resourceType = resourceType;
    }
}
