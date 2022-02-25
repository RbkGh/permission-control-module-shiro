package com.rodneyboachie.permcontrol.db;

import lombok.Data;

/**
 * author: acerbk
 * Date: 23/02/2022
 * Time: 1:43 am
 */
@Data
public class UserEntity {

    private Long id;

    private String username;

    private String password;

    private String userRole;

    private String permission;
}
