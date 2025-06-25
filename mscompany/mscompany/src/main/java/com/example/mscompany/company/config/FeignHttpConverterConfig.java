package com.example.mscompany.company.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Collections;
/*
@Configuration
public class FeignHttpConverterConfig {

    @Bean
    public HttpMessageConverters messageConverters() {
        HttpMessageConverter<?> converter = new MappingJackson2HttpMessageConverter();
        return new HttpMessageConverters(Collections.singletonList(converter));
    }
}
*/
@Configuration
public class FeignHttpConverterConfig {

    @Bean
    public HttpMessageConverters messageConverters() {
        return new HttpMessageConverters(new MappingJackson2HttpMessageConverter());
    }
}