package com.rodneyboachie.permcontrol.mapper;

import com.rodneyboachie.permcontrol.db.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * author: acerbk
 * Date: 23/02/2022
 * Time: 2:42 am
 */
@Mapper
public interface UsersMapper {
    @Select("SELECT * FROM USER WHERE username= #{username}")
    UserEntity getUserByUsername(@Param("username") String username);

    @Select("SELECT us.username,\n" +
            " us.password,\n" +
            " ur.rolename as userRole,\n" +
            " rp.permission as permission\n" +
            "FROM\n" +
            "  USER us\n" +
            "INNER JOIN\n" +
            "  (user_roles ur,role_permissions rp)\n" +
            "on\n" +
            " (us.username=ur.username) and (rp.rolename=ur.rolename)\n" +
            "WHERE\n" +
            "  us.username = #{username}")
    UserEntity getUserRoleAndPermission(@Param("username") String username);
}
