package com.wisestep.locationsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String errorMessage;

    private String errorCode;

}
