package br.com.desafio.totalshake.controllers;

import br.com.desafio.totalshake.dto.ItemPedidoDTO;
import br.com.desafio.totalshake.dto.PedidoDTO;
import br.com.desafio.totalshake.models.ItemPedido;
import br.com.desafio.totalshake.models.Pedido;
import br.com.desafio.totalshake.models.Status;
import br.com.desafio.totalshake.repository.ItemPedidoRepository;
import br.com.desafio.totalshake.repository.PedidoRepository;
import br.com.desafio.totalshake.service.ItemPedidoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemPedidoControllerTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Test
    void adicionarItem() {
        PedidoDTO testePedido = new PedidoDTO(Status.PAGO);
        Pedido pedidoCriado = pedidoRepository.save(Pedido.of(testePedido));
        ItemPedidoDTO testeItemPedido = new ItemPedidoDTO(pedidoCriado, "Shake de Banana", 2);
        ItemPedido itemPedido = itemPedidoRepository.save(ItemPedido.of(testeItemPedido));
        Assertions.assertEquals(pedidoCriado, itemPedido.getPedido());
        Assertions.assertEquals(testeItemPedido.getDescricao(), itemPedido.getDescricao());
        Assertions.assertEquals(testeItemPedido.getQuantidade(), itemPedido.getQuantidade());

    }

    @Test
    void findItemPedidoById() {
        PedidoDTO testePedido = new PedidoDTO(Status.PAGO);
        Pedido pedidoCriado = pedidoRepository.save(Pedido.of(testePedido));
        ItemPedidoDTO testeItemPedido = new ItemPedidoDTO(pedidoCriado, "Shake de Banana", 2);
        ItemPedido itemPedido = itemPedidoRepository.save(ItemPedido.of(testeItemPedido));
        Optional<ItemPedido> response = itemPedidoRepository.findById(itemPedido.getId());
        response.ifPresentOrElse(
                res -> Assertions.assertTrue(true),
                () -> Assertions.assertTrue(false));
    }

    @Test
    void findAll() {
        PedidoDTO testePedido = new PedidoDTO(Status.PAGO);
        Pedido pedidoCriado = pedidoRepository.save(Pedido.of(testePedido));
        ItemPedidoDTO testeItemPedido = new ItemPedidoDTO(pedidoCriado, "Shake de Banana", 2);
        ItemPedidoDTO testeItemPedido2 = new ItemPedidoDTO(pedidoCriado, "Shake de Morango", 4);
        ItemPedido itemPedido = itemPedidoRepository.save(ItemPedido.of(testeItemPedido));
        ItemPedido itemPedido2 = itemPedidoRepository.save(ItemPedido.of(testeItemPedido2));
        List<ItemPedido> response = itemPedidoRepository.findAll();
        Assertions.assertEquals(2, response.size());
        Assertions.assertTrue(response.contains(itemPedido));
        Assertions.assertTrue(response.contains(itemPedido2));
    }

    @Test
    void updateItemPedido() {
        PedidoDTO testePedido = new PedidoDTO(Status.PAGO);
        Pedido pedidoCriado = pedidoRepository.save(Pedido.of(testePedido));
        ItemPedidoDTO testeItemPedido = new ItemPedidoDTO(pedidoCriado, "Shake de Banana", 2);
        ItemPedido itemPedido = itemPedidoRepository.save(ItemPedido.of(testeItemPedido));
        itemPedido.setQuantidade(10);
        ItemPedido itemPedidoAtualizado = itemPedidoRepository.save(itemPedido);
        Assertions.assertNotEquals(testeItemPedido.getQuantidade(), itemPedidoAtualizado.getQuantidade());
    }

    @Test
    void deleteItemPedido() {
        PedidoDTO testePedido = new PedidoDTO(Status.PAGO);
        Pedido pedidoCriado = pedidoRepository.save(Pedido.of(testePedido));
        ItemPedidoDTO testeItemPedido = new ItemPedidoDTO(pedidoCriado, "Shake de Banana", 2);
        ItemPedido itemPedido = itemPedidoRepository.save(ItemPedido.of(testeItemPedido));
        itemPedidoRepository.delete(itemPedido);
        Optional<ItemPedido> response = itemPedidoRepository.findById(itemPedido.getId());
        response.ifPresentOrElse(
                res -> Assertions.assertTrue(false),
                () -> Assertions.assertTrue(true));
    }
}