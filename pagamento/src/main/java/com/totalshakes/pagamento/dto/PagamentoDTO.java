package com.totalshakes.pagamento.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.totalshakes.pagamento.models.FormaDePagamento;
import com.totalshakes.pagamento.models.Pagamento;
import com.totalshakes.pagamento.models.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@JsonPropertyOrder({"id_pagamento", "valor", "nome", "numero", "expiracao", "codigo", "status", "pedido_id", "formaDePagamento"})
public class PagamentoDTO {
    @JsonProperty("id_pagamento")
    private Long id;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private String nome;
    @NotNull
    private String numero;
    private String expiracao;
    @NotNull
    private String codigo;
    @NotNull
    private Status status;
    @JsonProperty("pedido_id")
    private Long pedidoId;
    private FormaDePagamento formaDePagamento;

    public PagamentoDTO() {}

    public PagamentoDTO(BigDecimal valor, String nome, String numero, String expiracao, String codigo, Status status, Long pedidoId, FormaDePagamento formaDePagamento) {
        this.valor = valor;
        this.nome = nome;
        this.numero = numero;
        this.expiracao = expiracao;
        this.codigo = codigo;
        this.status = status;
        this.pedidoId = pedidoId;
        this.formaDePagamento = formaDePagamento;
    }

    public PagamentoDTO(Long id, BigDecimal valor, String nome, String numero, String expiracao, String codigo, Status status, Long pedidoId, FormaDePagamento formaDePagamento) {
        this.id = id;
        this.valor = valor;
        this.nome = nome;
        this.numero = numero;
        this.expiracao = expiracao;
        this.codigo = codigo;
        this.status = status;
        this.pedidoId = pedidoId;
        this.formaDePagamento = formaDePagamento;
    }

    public static PagamentoDTO of(Pagamento pagamento) {
        var dto = new PagamentoDTO();
        dto.setId(pagamento.getId());
        dto.setValor(pagamento.getValor());
        dto.setNome(pagamento.getNome());
        dto.setNumero(pagamento.getNumero());
        dto.setExpiracao(pagamento.getExpiracao());
        dto.setCodigo(pagamento.getCodigo());
        dto.setStatus(pagamento.getStatus());
        dto.setPedidoId(pagamento.getPedidoId());
        dto.setFormaDePagamento(pagamento.getFormaDePagamento());
        return dto;
    }
}
