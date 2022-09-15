package br.com.desafio.totalshake.exception;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime timestamp, String message, String detail) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

}
