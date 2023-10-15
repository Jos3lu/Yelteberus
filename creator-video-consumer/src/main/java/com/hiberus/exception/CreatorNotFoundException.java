package com.hiberus.exception;

public class CreatorNotFoundException extends Exception {
    public CreatorNotFoundException(String creatorId) {
        super("Creator not found: " + creatorId);
    }
}
