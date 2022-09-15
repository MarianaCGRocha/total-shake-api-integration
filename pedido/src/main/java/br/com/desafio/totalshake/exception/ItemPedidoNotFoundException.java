package br.com.desafio.totalshake.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemPedidoNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ItemPedidoNotFoundException(String ex) {
        super(ex);
    }
    public ItemPedidoNotFoundException() {
        super("Item not found.");
    }
}
