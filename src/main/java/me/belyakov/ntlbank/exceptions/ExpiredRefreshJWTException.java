package me.belyakov.ntlbank.exceptions;

public class ExpiredRefreshJWTException extends ExpiredJWTException {

    public ExpiredRefreshJWTException(String additionalInfo) {
        super("REFRESH", additionalInfo);
    }

    public ExpiredRefreshJWTException() {
        super("REFRESH", "");
    }
}
