package com.rodneyboachie.permcontrol.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * author: acerbk
 * Date: 23/02/2022
 * Time: 11:00 am
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    private Long id;

    private String username;

    private String productname;

    private BigDecimal productprice;

    private BigDecimal productqty;

}
