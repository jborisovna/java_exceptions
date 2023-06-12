package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {
    ShopRepository repo = new ShopRepository();

    Product product1 = new Product(1, "Футболка", 500);
    Product product2 = new Product(2, "Ботинки", 1500);
    Product product3 = new Product(3, "Пальто", 3500);

    @BeforeEach
    public void setup() {
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
    }

    @Test
    public void shouldRemoveById() {

        repo.remove(product2.getId());

        Product[] expected = {product1, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionWhenRemoveById() {

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(5);
        });
    }

    @Test
    public void shouldAddProduct() {
        Product product8 = new Product(8, "Панама", 300);
        repo.add(product8);

        Product[] expected = {product1, product2, product3, product8};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldTrowExceptionWhenAddDuplicate() {
        Product product4 = new Product(1, "Рубашка", 800);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(product4);
        });
    }
}
