package me.belyakov.ntlbank.exceptions;

public class BadRefreshJWTException extends BadJWTException {

    public BadRefreshJWTException(String additionalInfo) {
        super("REFRESH", additionalInfo);
    }

    public BadRefreshJWTException() {
        super("REFRESH", "");
    }

}