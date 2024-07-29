package com.example.demo.test.integration.product.overridingBean.customImplementation

import com.example.demo.test.integration.product.http.client.ProductHttpClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@TestConfiguration
class SpecConfig {

    @Value('${product.externalService.url}')
    private final String url
    @Autowired
    private final RestTemplate restTemplate

    @Bean
    ProductHttpClient productHttpClient() {
        new CustomProductHttpClient(url, restTemplate)
    }
}
