package com.rodneyboachie.permcontrol.controllers;

import com.rodneyboachie.permcontrol.controllers.dto.BaseResponseDTO;
import com.rodneyboachie.permcontrol.controllers.dto.ProductDTO;
import com.rodneyboachie.permcontrol.service.ProductService;
import com.rodneyboachie.permcontrol.util.JWTUtil;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * author: acerbk
 * Date: 22/02/2022
 * Time: 8:58 pm
 */
@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    @RequiresRoles("buyer")
    public ResponseEntity<?> getAllProductsAvailable() {
        return ResponseEntity.ok(new BaseResponseDTO("Success"
                , productService.getAllProducts()));
    }

    @GetMapping("/products/user/{username}")
    @RequiresRoles("seller")
    public ResponseEntity<?> getAllProductsListedBySeller(@RequestHeader(value = "Authorization") String authorizationHeaderValue,
                                                          @PathVariable String username) throws AuthorizationException {
        String token = authorizationHeaderValue.replace("Bearer" + " ", "");
        //if user's provided username is not the same as the username in token,throw authorization exception
        if (!JWTUtil.getUsername(token).equals(username))
            throw new AuthorizationException();
        return ResponseEntity.ok(new BaseResponseDTO("Success"
                , productService.getAllProductsListedBySeller(username)
                .stream()
                .map(productEntity -> new ProductDTO(productEntity.getUsername(),
                        productEntity.getProductname(),
                        productEntity.getProductprice(),
                        productEntity.getProductqty())
                )));
    }

    @PostMapping("/products")
    @RequiresRoles("seller")
    public ResponseEntity<?> createProduct(@RequestHeader(value = "Authorization") String authorizationHeaderValue,
                                           @RequestBody ProductDTO productRequestDTO) throws AuthorizationException {

        String token = authorizationHeaderValue.replace("Bearer" + " ", "");
        String username = JWTUtil.getUsername(token);

        //setProduct owner from token request so that client can't create products for different seller aside him/herself
        productRequestDTO.setProductOwner(username);

        int productID = productService.createProduct(productRequestDTO);

        //set url location of saved product
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/products/{productID}")
                .buildAndExpand(productID)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
