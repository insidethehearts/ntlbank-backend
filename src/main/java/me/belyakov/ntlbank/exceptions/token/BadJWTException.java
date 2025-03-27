package me.belyakov.ntlbank.exceptions.token;

public abstract class BadJWTException extends RuntimeException {

    public BadJWTException(String tokenType, String additionalInfo) {
        super(String.format("Bad token with type '%s'. %s", tokenType, additionalInfo));
    }
}