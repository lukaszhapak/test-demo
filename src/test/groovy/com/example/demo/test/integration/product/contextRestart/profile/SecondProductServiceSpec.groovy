package com.example.demo.test.integration.product.contextRestart.profile

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("non-existing-profile")
class SecondProductServiceSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    def "should save product"() {
        when:
        Long id = productService.save(sampleProduct).getId()

        then:
        productService.getById(id) != null
    }
}
