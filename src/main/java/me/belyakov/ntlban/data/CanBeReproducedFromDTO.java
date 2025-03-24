package me.belyakov.ntlban.data;

public interface CanBeReproducedFromDTO<T> {

    void reproduceFromDTO(T dtoObject);

}