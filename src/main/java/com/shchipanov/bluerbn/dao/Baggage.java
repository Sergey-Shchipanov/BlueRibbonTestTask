package com.shchipanov.bluerbn.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Baggage {
    private String baggageId;
    private int destinationId;
}
