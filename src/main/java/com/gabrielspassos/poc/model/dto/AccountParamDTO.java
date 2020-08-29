package com.gabrielspassos.poc.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountParamDTO {

    private Long bank;
    private Long agency;
    private Long number;

}
