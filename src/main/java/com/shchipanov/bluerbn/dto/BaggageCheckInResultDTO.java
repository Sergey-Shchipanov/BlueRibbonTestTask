package com.shchipanov.bluerbn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaggageCheckInResultDTO {
    private int destinationId;
    private String baggageId;
    private boolean checkedIn;
}
