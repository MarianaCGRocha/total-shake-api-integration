package com.totalshakes.pagamento.service;

import com.totalshakes.pagamento.client.PedidoClient;
import com.totalshakes.pagamento.dto.PagamentoDTO;
import com.totalshakes.pagamento.models.Pagamento;
import com.totalshakes.pagamento.models.Status;
import com.totalshakes.pagamento.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private PedidoClient client;

    public PagamentoDTO create(PagamentoDTO request) {
        Pagamento pagamento = repository.save(Pagamento.of(request));
        return PagamentoDTO.of(pagamento);
    }

    public PagamentoDTO findById(Long id) {
        Pagamento pagamento = repository.findById(id).orElseThrow();
        return PagamentoDTO.of(pagamento);
    }

    public void delete(Long id) {
        Pagamento pagamento = repository.findById(id).orElseThrow();
        repository.delete(pagamento);
    }

    public PagamentoDTO updateStatus(Long id, Status status) {
        Pagamento pagamento = repository.findById(id).orElseThrow();
        pagamento.setStatus(status);
        if(status == Status.CONFIRMADO) {
            client.update(pagamento.getPedidoId(), "PAGO");
        }
        return PagamentoDTO.of(repository.save(pagamento));
    }

    public List<PagamentoDTO> findAll() {
        List<Pagamento> pagamentos = repository.findAll();
        return pagamentos.stream().map(PagamentoDTO::of).collect(Collectors.toList());
    }
}
