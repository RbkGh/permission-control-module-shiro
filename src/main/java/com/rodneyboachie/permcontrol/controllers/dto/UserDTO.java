package com.rodneyboachie.permcontrol.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author: acerbk
 * Date: 23/02/2022
 * Time: 9:40 pm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private String userRole;

    private String permission;

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
