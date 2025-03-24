package me.belyakov.ntlbank.exceptions;

public class ExpiredAccessJWTException extends ExpiredJWTException {

    public ExpiredAccessJWTException(String additionalInfo) {
        super("ACCESS", additionalInfo);
    }

    public ExpiredAccessJWTException() {
        super("ACCESS", "");
    }
}
