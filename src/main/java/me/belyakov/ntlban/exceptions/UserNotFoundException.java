package me.belyakov.ntlban.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long userId) {
        super(String.format("User with id '%s' not found.", userId));
    }
}
