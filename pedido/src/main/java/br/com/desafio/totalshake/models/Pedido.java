package br.com.desafio.totalshake.models;

import br.com.desafio.totalshake.dto.PedidoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    public Long Id;
    @Column(name = "dataHora", nullable = false)
    public LocalDateTime dataHora;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    public Status status;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
    @JsonIgnore
    public List<ItemPedido> pedidos;

    @PrePersist
    void prePersist() {
        this.dataHora = LocalDateTime.now();
    }

    public static Pedido of(PedidoDTO request) {
        Pedido pedido = new Pedido();

        pedido.setStatus(request.getStatus());
        pedido.setDataHora(request.getDataHora());
        return pedido;
    }

    public void addItemPedido(ItemPedido itemPedido) {
        pedidos.add(itemPedido);
    }
}
