package com.ikhz.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorResponse {
    private String Error;
    private List<String> message = new ArrayList();

    public ErrorResponse(String message){
        this.Error = message;
    }
}
