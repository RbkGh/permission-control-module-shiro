package com.rodneyboachie.permcontrol.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * author: acerbk
 * Date: 23/02/2022
 * Time: 9:46 pm
 */
@Data
@AllArgsConstructor
public class BaseResponseDTO {

    private String message;

    private Object data;
}
