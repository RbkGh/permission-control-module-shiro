package com.rodneyboachie.permcontrol.service;

import com.rodneyboachie.permcontrol.controllers.dto.ProductDTO;
import com.rodneyboachie.permcontrol.db.ProductEntity;
import com.rodneyboachie.permcontrol.mapper.ProductsMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: acerbk
 * Date: 24/02/2022
 * Time: 4:15 am
 */
@Service
@AllArgsConstructor
public class ProductService {

    private ProductsMapper productMapper;

    public List<ProductEntity> getAllProducts() {
        return productMapper.getAllProducts();
    }

    public List<ProductEntity> getAllProductsListedBySeller(String usernameOfSeller) {
        return productMapper.getProductsBelongingToUser(usernameOfSeller);
    }

    public int createProduct(ProductDTO productRequestDTO) {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setUsername(productRequestDTO.getProductOwner());
        productEntity.setProductname(productRequestDTO.getProductName());
        productEntity.setProductqty(productRequestDTO.getProductQty());
        productEntity.setProductprice(productRequestDTO.getProductPrice());

        productMapper.insertProduct(productEntity);
        return productMapper.selectLastInsertId();
    }
}
