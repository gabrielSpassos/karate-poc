package com.gabrielspassos.poc.controler.v1;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public interface BaseVersion {

    int OK = 200;
    String OK_MESSAGE = "Successfully Operation";
}
