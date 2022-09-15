package br.com.desafio.totalshake.dto;

import br.com.desafio.totalshake.models.ItemPedido;
import br.com.desafio.totalshake.models.Pedido;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@JsonPropertyOrder({"id", "descricao", "quantidade", "idPedido"})
@JsonIgnoreProperties("pedido")
public class ItemPedidoDTO {

    private Pedido pedido;
    private Long idItemPedido;
    @NotEmpty
    private String descricao;
    @NotNull
    private Integer quantidade;
    @NotNull
    private Long idPedido;

    public ItemPedidoDTO() {}

    public ItemPedidoDTO(String descricao, Integer quantidade, Long idPedido) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.idPedido = idPedido;
    }

    public ItemPedidoDTO(Pedido pedido, String descricao, Integer quantidade) {
        this.pedido = pedido;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.idPedido = pedido.getId();
    }

    public ItemPedidoDTO(Pedido pedido, Long idItemPedido, String descricao, Integer quantidade) {
        this.pedido = pedido;
        this.idItemPedido = idItemPedido;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.idPedido = pedido.getId();
    }

    public static ItemPedidoDTO of(ItemPedido itemPedido) {
        ItemPedidoDTO dto = new ItemPedidoDTO(
                itemPedido.getPedido(),
                itemPedido.getId(),
                itemPedido.getDescricao(),
                itemPedido.getQuantidade()
        );
        return dto;
    }
}
