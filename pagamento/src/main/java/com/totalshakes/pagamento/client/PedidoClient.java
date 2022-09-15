package com.totalshakes.pagamento.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "pedido", url = "http://localhost:8081/api/pedido")
public interface PedidoClient {
    @RequestMapping(method = RequestMethod.PUT, value = "/{idPedido}/{status}")
    void update(@PathVariable("idPedido") Long idPedido, @PathVariable("status") String status);

}
