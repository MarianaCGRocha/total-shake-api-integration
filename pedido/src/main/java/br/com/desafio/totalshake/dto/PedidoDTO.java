package br.com.desafio.totalshake.dto;

import br.com.desafio.totalshake.models.Pedido;
import br.com.desafio.totalshake.models.Status;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonPropertyOrder({"pedido_id", "dataHora", "status", "pedidos"})
public class PedidoDTO {

    @JsonProperty("pedido_id")
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // The most common ISO Date Time Format yyyy-MM-dd'T'HH:mm:ss.SSSXXX — for example, "2000-10-31T01:30:00.000-05:00".
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataHora;
    @NotNull
    private Status status;
    private List<ItemPedidoDTO> pedidos;

    public PedidoDTO(Status status) {
        this.status = status;
    }

    public PedidoDTO(Long Id, LocalDateTime dataHora, Status status) {
        this.id = Id;
        this.dataHora = dataHora;
        this.status = status;
    }

    public static PedidoDTO of(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO(
                pedido.getId(),
                pedido.getDataHora(),
                pedido.getStatus()
        );

        if (pedido.getPedidos() != null) {
            dto.setPedidos(pedido.getPedidos().stream().map(ItemPedidoDTO::of).collect(Collectors.toList()));
        }

        return dto;
    }
}