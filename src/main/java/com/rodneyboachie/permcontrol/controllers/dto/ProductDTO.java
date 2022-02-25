package com.rodneyboachie.permcontrol.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * author: acerbk
 * Date: 24/02/2022
 * Time: 2:48 pm
 */
@Data
@AllArgsConstructor
public class ProductDTO {
    private String productOwner;

    private String productName;

    private BigDecimal productPrice;

    private BigDecimal productQty;
}
