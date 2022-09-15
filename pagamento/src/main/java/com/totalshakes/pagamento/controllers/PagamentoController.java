package com.totalshakes.pagamento.controllers;

import com.totalshakes.pagamento.dto.PagamentoDTO;
import com.totalshakes.pagamento.models.Status;
import com.totalshakes.pagamento.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
    @Autowired
    private PagamentoService service;

    @GetMapping("/{idPagamento}")
    public PagamentoDTO findById(@PathVariable Long idPagamento) {
        return service.findById(idPagamento);
    }

    @GetMapping
    public Iterable<PagamentoDTO> findAll() {
        return service.findAll();
    }

    @PostMapping
    public PagamentoDTO create(@RequestBody PagamentoDTO pagamento) {
        return service.create(pagamento);
    }

    @DeleteMapping("/{idPagamento}")
    public void delete(@PathVariable Long idPagamento) {
        service.delete(idPagamento);
    }

    @PutMapping("/{idPagamento}/status/{status}")
    public PagamentoDTO updateStatus(@PathVariable Long idPagamento, @PathVariable Status status) {
        return service.updateStatus(idPagamento, status);
    }
}
