package com.carloz.fintech.fintechapi.web;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * The class ExceptionData is a model for the web service request that has an Exception,
 * all this information will be returned to the client.
 *
 * @author Carlo Santovito
 */
@Builder
@Getter
@Setter
public class ExceptionData {

    private Instant exceptionTime;

    private String path = "";

    private int responseStatusCode;

    private String responseStatusText;

    private String exceptionClassType;

    private String exceptionMessage;

}
