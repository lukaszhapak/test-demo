package com.example.demo.test.integration.product.overridingBean.customImplementation

import com.example.demo.test.integration.product.http.client.ProductHttpClient
import org.springframework.web.client.RestTemplate

class CustomProductHttpClient extends ProductHttpClient {
    CustomProductHttpClient(String url, RestTemplate restTemplate) {
        super(url, restTemplate)
    }

    @Override
    String getValue() {
        "321"
    }
}
