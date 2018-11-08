package com.victorseger.svtech.services;


import com.victorseger.svtech.services.exceptions.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class PedidoServiceTest {

    private static final int ORDERS = 3;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProdutoService produtoService;

    @Test
    public void test_getOne_should_retrieve_order_by_id() {
        assertThat(pedidoService.find(1)).isNotNull();
    }

    @Test
    public void test_getOne_id_null() {
        assertThat(pedidoService.find(null)).isNull();
    }

    @Test(expected = ObjectNotFoundException.class)
    public void test_getOne_id_invalid() {
        pedidoService.find(-1);
    }

    @Test
    public void test_findAll() {
        assertThat(pedidoService.findAll()).size().isEqualTo(ORDERS);
    }

    @Test
    public void test_existsById_id_invalid() {
        assertThat(pedidoService.existsById(-1)).isFalse();
    }

    @Test
    public void test_existsById_id_null() {
        assertThat(pedidoService.existsById(null)).isFalse();
    }

    @Test
    public void test_find_null() {
        assertThat(pedidoService.find(null)).isNull();
    }

    @Test(expected = ObjectNotFoundException.class)
    public void test_find_invalid() {
        pedidoService.find(-1);
    }

    @Test
    public void test_find() {
        assertThat(pedidoService.find(1)).isNotNull();
    }

    @Test
    public void test_insert_null() {
        assertThat(pedidoService.insert(null)).isNull();
    }

    @Test(expected = ObjectNotFoundException.class)
    public void test_delete_invalid() {
        pedidoService.delete(-1);
    }

    @Test
    public void test_update() {
        assertThat(pedidoService.update(pedidoService.find(1))).isNotNull();
    }

    @Test
    public void test_update_null() {
        assertThat(pedidoService.update(null)).isNull();
    }

    @Test
    public void test_findItemById_null() {
        assertThat(pedidoService.findItemById(null, null)).isNull();
    }



}