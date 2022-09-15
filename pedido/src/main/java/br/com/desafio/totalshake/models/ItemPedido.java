package br.com.desafio.totalshake.models;

import br.com.desafio.totalshake.dto.ItemPedidoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "item_pedido")
@Data
public class ItemPedido {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_pedido", nullable = false)
    private Pedido pedido;

    public static ItemPedido of(ItemPedidoDTO dto) {
        ItemPedido itemPedido = new ItemPedido();

        itemPedido.setPedido(dto.getPedido());
        itemPedido.setDescricao(dto.getDescricao());
        itemPedido.setQuantidade(dto.getQuantidade());

        return itemPedido;
    }

}
