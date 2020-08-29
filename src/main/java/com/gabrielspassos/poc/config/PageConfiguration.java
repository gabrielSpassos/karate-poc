package com.gabrielspassos.poc.config;

import org.springframework.data.domain.PageRequest;

public interface PageConfiguration {

    String PAGE = "0";
    String SIZE = "60";
    PageRequest DEFAULT_PAGE = PageRequest.of(0, 60);

}
