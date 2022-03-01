package com.rodneyboachie.permcontrol.mapper;

import com.rodneyboachie.permcontrol.db.ProductEntity;
import com.rodneyboachie.permcontrol.helper.MySQLContainerContextInitializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * author: acerbk
 * Date: 23/02/2022
 * Time: 8:04 pm
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = { MySQLContainerContextInitializer.class })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductMapperTest {

    @Mock
    private ProductsMapper productMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(new ProductEntity
                (1L, "max", "Samsung 21inch TV", new BigDecimal(300), new BigDecimal(20)));
        productEntities.add(new ProductEntity
                (1L, "max", "LG 21inch TV", new BigDecimal(440), new BigDecimal(35)));

        Mockito.when(productMapper.getProductsBelongingToUser("max"))
                .thenReturn(productEntities);
    }

    @Test
    public void should_get_products_belonging_to_buyer_only() {
        List<ProductEntity> productEntities = productMapper.getProductsBelongingToUser("max");

        Assert.assertTrue(productEntities.size() == 2 && productEntities.get(1).getProductprice().equals(new BigDecimal(440)));
    }
}
