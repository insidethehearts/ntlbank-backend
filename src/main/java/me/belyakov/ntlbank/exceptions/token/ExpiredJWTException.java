package me.belyakov.ntlbank.exceptions.token;

public abstract class ExpiredJWTException extends RuntimeException {

    public ExpiredJWTException(String tokenType, String additionalInfo) {
        super(String.format("Expired token with type '%s'. %s", tokenType, additionalInfo));
    }
}