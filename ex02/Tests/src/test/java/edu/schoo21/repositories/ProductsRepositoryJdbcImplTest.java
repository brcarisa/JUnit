package edu.schoo21.repositories;

import edu.school21.models.Product;
import edu.school21.repositories.ProductsRepository;
import edu.school21.repositories.ProductsRepositoryJdbclmpl;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class ProductsRepositoryJdbcImplTest {

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "iPhone", 100000),
            new Product(2L, "Samsung", 88000),
            new Product(3L, "Pixel", 77000),
            new Product(4L, "Blackberry", 55000),
            new Product(5L, "Xiomi", 15000),
            new Product(6L, "Meizu", 23000),
            new Product(7L, "Nokia", 10000),
            new Product(8L, "HTC", 35000)
    );

    final List<Product> EXPECTED_DELETED_PRODUCTS = Arrays.asList(
            new Product(2L, "Samsung", 88000),
            new Product(3L, "Pixel", 77000),
            new Product(4L, "Blackberry", 55000),
            new Product(5L, "Xiomi", 15000),
            new Product(6L, "Meizu", 23000),
            new Product(7L, "Nokia", 10000),
            new Product(8L, "HTC", 35000)
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(6L, "Meizu", 23000);

    final Product EXPECTED_UPDATED_PRODUCT = new Product(3L, "DNS", 7000);

    final Product EXPECTED_SAVE_PRODUCT = new Product(9L, "Philips", 49000);


    private EmbeddedDatabase embeddedDatabase;
    private ProductsRepository productsRepository;

    @BeforeEach
    public void init() {
        embeddedDatabase = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("schema.sql").addScript("data.sql").build();
        productsRepository = new ProductsRepositoryJdbclmpl(embeddedDatabase);
    }

    @Test
    public void findAll() {
        List<Product> list = productsRepository.findAll();
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, list);
    }

    @Test
    public void findById() {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsRepository.findById(6L).get());
        Assertions.assertEquals(Optional.empty(), productsRepository.findById(56L));
        Assertions.assertEquals(Optional.empty(), productsRepository.findById(88L));
    }

    @Test
    public void save() {
        productsRepository.save(new Product(9L, "Philips", 49000));
        Assertions.assertEquals(EXPECTED_SAVE_PRODUCT, productsRepository.findById(9L).get());
    }

    @Test
    public void updateTest(){
        productsRepository.update(new Product(3L, "DNS", 7000));
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, productsRepository.findById(3L).get());
    }

    @Test
    public void deleteTest(){
        productsRepository.delete(1L);
        Assertions.assertEquals(EXPECTED_DELETED_PRODUCTS, productsRepository.findAll());
        Assertions.assertFalse(productsRepository.findById(1L).isPresent());
    }

    @AfterEach
    public void closeDB(){
        embeddedDatabase.shutdown();
    }

}



























