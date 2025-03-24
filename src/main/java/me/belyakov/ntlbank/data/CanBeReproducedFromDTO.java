package me.belyakov.ntlbank.data;

public interface CanBeReproducedFromDTO<T> {

    void reproduceFromDTO(T dtoObject);

}