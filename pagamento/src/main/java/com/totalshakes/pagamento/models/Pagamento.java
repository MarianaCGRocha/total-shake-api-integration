package com.totalshakes.pagamento.models;

import com.totalshakes.pagamento.dto.PagamentoDTO;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento" , nullable = false)
    private Long id;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @Size(max = 100, message = "Nome muito longo.")
    private String nome;
    @NotNull
    @Size(max = 100, message = "Número muito longo.")
    private String numero;
    private String expiracao;
    @NotNull
    @Size(max = 3, min = 3, message = "O código deve ter 3 dígitos.")
    private String codigo;
    @NotNull
    @Enumerated
    private Status status;
    @Column(name = "pedido_id" , nullable = false)
    private Long pedidoId;
    private FormaDePagamento formaDePagamento;

    public Pagamento() {}

    public Pagamento(Long id, BigDecimal valor, String nome, String numero, String expiracao, String codigo, Status status, Long pedidoId, FormaDePagamento formaDePagamento) {
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

    public static Pagamento of(PagamentoDTO dto) {
        var pagamento = new Pagamento();
        pagamento.setId(dto.getId());
        pagamento.setValor(dto.getValor());
        pagamento.setNome(dto.getNome());
        pagamento.setNumero(dto.getNumero());
        pagamento.setExpiracao(dto.getExpiracao());
        pagamento.setCodigo(dto.getCodigo());
        pagamento.setStatus(dto.getStatus());
        pagamento.setPedidoId(dto.getPedidoId());
        pagamento.setFormaDePagamento(dto.getFormaDePagamento());
        return pagamento;
    }
}
