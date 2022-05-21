package com.example.accenture.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "${spring.jsonplaceholder.url}", name = "${spring.jsonplaceholder.name}")
public interface JsonPlaceHolder {


}
