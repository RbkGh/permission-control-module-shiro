package com.rodneyboachie.permcontrol.mapper;

import com.rodneyboachie.permcontrol.db.ProductEntity;
import com.rodneyboachie.permcontrol.db.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * author: acerbk
 * Date: 23/02/2022
 * Time: 2:22 am
 */
@Mapper
public interface ProductsMapper {
    @Select("select * from product where username=#{username}")
    List<ProductEntity> getProductsBelongingToUser(@Param("username") String username);

    @Select("select * from product")
    List<ProductEntity> getAllProducts();

    @Insert("insert ignore into product(username,productname,productprice,productqty) values " +
            "(#{username},#{productname}" +
            ",#{productprice},#{productqty})")
    void insertProduct(ProductEntity productEntity);

    @Select("SELECT LAST_INSERT_ID()")
    int selectLastInsertId();
}
