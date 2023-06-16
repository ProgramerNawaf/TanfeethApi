package com.example.tanfeeth.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RequestDTO {
    @NotNull(message = "Offer can't be null!")
    private Double offer;
    private String status;
    private Integer projectId;
    private Integer operationCompanyId;

}
