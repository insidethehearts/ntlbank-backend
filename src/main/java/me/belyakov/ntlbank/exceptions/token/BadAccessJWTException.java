package me.belyakov.ntlbank.exceptions.token;

public class BadAccessJWTException extends BadJWTException {

    public BadAccessJWTException(String additionalInfo) {
        super("ACCESS", additionalInfo);
    }

    public BadAccessJWTException() {
        super("ACCESS", "");
    }

}