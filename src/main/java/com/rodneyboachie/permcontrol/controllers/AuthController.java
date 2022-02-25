package com.rodneyboachie.permcontrol.controllers;

import com.rodneyboachie.permcontrol.controllers.dto.UserDTO;
import com.rodneyboachie.permcontrol.exceptions.UnauthorizedException;
import com.rodneyboachie.permcontrol.security.JWTConfig;
import com.rodneyboachie.permcontrol.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: acerbk
 * Date: 23/02/2022
 * Time: 9:02 pm
 */
@RestController
@AllArgsConstructor
public class AuthController {

    private AuthService authService;
    private final JWTConfig config;

    /**
     * jwt is inserted in the header reponse instead of the body
     *
     * @param userDTO
     * @return
     * @throws UnauthorizedException
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDTO userDTO)
            throws UnauthorizedException {
        String jwtToken = authService.getAuthorizedToken(userDTO.getUsername(), userDTO.getPassword());

        return ResponseEntity.ok()
                .header(config.getHeader(), config.getPrefix() + " " + jwtToken)
                .build();
    }
}
