package br.com.desafio.totalshake.controllers;

import br.com.desafio.totalshake.dto.PedidoDTO;
import br.com.desafio.totalshake.models.Pedido;
import br.com.desafio.totalshake.models.Status;
import br.com.desafio.totalshake.repository.PedidoRepository;
import org.junit.jupiter.api.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PedidoControllerTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Test
    void create() {
        PedidoDTO testePedido = new PedidoDTO(Status.PAGO);
        Pedido response = pedidoRepository.save(Pedido.of(testePedido));
        Assertions.assertEquals(testePedido.getStatus(), response.getStatus());
    }

    @Test
    void findById() {
        PedidoDTO testePedido = new PedidoDTO(Status.PAGO);
        Pedido pedidoCriado = pedidoRepository.save(Pedido.of(testePedido));
        Optional<Pedido> response = pedidoRepository.findById(pedidoCriado.getId());
        response.ifPresentOrElse(
                res -> Assertions.assertEquals(pedidoCriado.getStatus(), res.getStatus()),
                () -> Assertions.assertTrue(false));
    }

    @Test
    void findAll() {
        PedidoDTO testePedido = new PedidoDTO(Status.PAGO);
        PedidoDTO testePedido2 = new PedidoDTO(Status.ENTREGUE);
        Pedido pedidoCriado = pedidoRepository.save(Pedido.of(testePedido));
        Pedido pedidoCriado2 = pedidoRepository.save(Pedido.of(testePedido2));
        List<Pedido> response = pedidoRepository.findAll();
        Assertions.assertEquals(2, response.size());
        Assertions.assertTrue(response.contains(pedidoCriado));
        Assertions.assertTrue(response.contains(pedidoCriado2));
    }

    @Test
    void updateStatus() {
        PedidoDTO testePedido = new PedidoDTO(Status.PAGO);
        Pedido pedidoCriado = pedidoRepository.save(Pedido.of(testePedido));
        pedidoCriado.setStatus(Status.CANCELADO);
        Pedido pedidoAtualizado = pedidoRepository.save(pedidoCriado);
        Assertions.assertNotEquals(testePedido.getStatus(), pedidoAtualizado.getStatus());
    }

    @Test
    void delete() {
        PedidoDTO testePedido = new PedidoDTO(Status.PAGO);
        Pedido pedidoCriado = pedidoRepository.save(Pedido.of(testePedido));
        pedidoRepository.delete(pedidoCriado);
        Optional<Pedido> response = pedidoRepository.findById(pedidoCriado.getId());
        response.ifPresentOrElse(
                res -> Assertions.assertTrue(false),
                () -> Assertions.assertTrue(true));
    }
}