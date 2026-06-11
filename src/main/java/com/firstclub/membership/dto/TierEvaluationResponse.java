package com.firstclub.membership.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TierEvaluationResponse {

    @NotBlank(message = "User id is required")
    private String userId;

    @NotBlank(message = "User id is required")
    private boolean eligible;

    @NotBlank(message = "User id is required")
    private String message;
}