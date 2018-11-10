package com.victorseger.svtech.services;

import com.victorseger.svtech.domain.Produto;
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
public class ProdutoServiceTest {

    private static final int PRODUCTS = 50;

    @Autowired
    private ProdutoService produtoService;

    @Test
    public void test_getOne_should_retrieve_product_by_id() {
        assertThat(produtoService.find(1)).isNotNull();
    }

    @Test
    public void test_getOne_id_null() {
        assertThat(produtoService.find(null)).isNull();
    }

    @Test(expected = ObjectNotFoundException.class)
    public void test_getOne_id_invalid() {
        produtoService.find(-1);
    }

    @Test
    public void test_findAll() {
        assertThat(produtoService.findAll()).size().isEqualTo(PRODUCTS);
    }

    @Test
    public void test_find_null() {
        assertThat(produtoService.find(null)).isNull();
    }

    @Test(expected = ObjectNotFoundException.class)
    public void test_find_invalid() {
        produtoService.find(-1);
    }

    @Test
    public void test_find() {
        assertThat(produtoService.find(1)).isNotNull();
    }

    @Test
    public void test_save() {
        assertThat(produtoService.save(new Produto(null, "Nome",10, 10.0))).isNotNull();
    }

    @Test
    public void test_save_null_object() {
        assertThat(produtoService.save(null)).isNull();
    }

    @Test
    public void test_delete_id_invalid() {
        assertThat(produtoService.delete(-1)).isFalse();
    }

    @Test
    public void test_delete_id_null() {
        assertThat(produtoService.delete(null)).isFalse();
    }

    @Test
    public void test_delete() {
        assertThat(produtoService.delete(1)).isTrue();
    }
}