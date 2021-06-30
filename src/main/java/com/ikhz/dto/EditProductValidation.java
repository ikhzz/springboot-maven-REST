package com.ikhz.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class EditProductValidation {

    @NotNull(message = "Product id required")
    private long productId;

    @NotNull(message = "amount is required")
    @Min(0)
    private long amount;
}
