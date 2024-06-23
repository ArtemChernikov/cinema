package ru.cinema.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 23.06.2024
 */
public class CustomErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new NotFoundException("Ресурс не найден");
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
