package br.com.desafio.totalshake.controllers;

import br.com.desafio.totalshake.dto.PedidoDTO;
import br.com.desafio.totalshake.models.Pedido;
import br.com.desafio.totalshake.models.Status;
import br.com.desafio.totalshake.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PedidoDTO pedido) {
        PedidoDTO pedidoResponse = this.pedidoService.create(pedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idPedido}").buildAndExpand(pedidoResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(pedidoResponse);
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<?> findById(@PathVariable Long idPedido) {
        return ResponseEntity.ok(pedidoService.findById(idPedido));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @PutMapping("/{idPedido}/{status}")
    public ResponseEntity<?> updateStatus(@PathVariable Long idPedido, @PathVariable Status status) {
        return ResponseEntity.ok(pedidoService.updateStatus(idPedido, status));
    }

    @DeleteMapping("/{idPedido}")
    public ResponseEntity<?> delete(@PathVariable Long idPedido) {
        pedidoService.delete(idPedido);
        return ResponseEntity.ok("Nº do pedido apagado: " + idPedido);
    }

}
