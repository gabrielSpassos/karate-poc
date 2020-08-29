package com.gabrielspassos.poc.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountDTO {

    private Long id;
    private Long bank;
    private Long agency;
    private Long number;
}
