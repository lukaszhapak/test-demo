package com.example.demo.test.integration.product.overridingBean.customImplementation

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import spock.lang.Specification

@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
@Import(SpecConfig)
class ProductServiceSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    def "should save product"() {
        when:
        Long id = productService.save(sampleProduct).getId()
        productService.assignValueFromExternalService(id)

        then:
        productService.getById(id) != null
        productService.getById(id).getClientValue() == "321"
    }
}
