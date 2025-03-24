package me.belyakov.ntlbank.exceptions;

public class BadAccessJWTException extends BadJWTException {

    public BadAccessJWTException(String additionalInfo) {
        super("ACCESS", additionalInfo);
    }

    public BadAccessJWTException() {
        super("ACCESS", "");
    }

}