package com.example.demo.test.integration.product.overridingBean.springBeanAnnotation

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.http.client.ProductHttpClient
import com.example.demo.test.integration.product.service.ProductService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ProductServiceSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    @SpringBean
    ProductHttpClient productHttpClient = Stub()

    def "should save product"() {
        given:
        productHttpClient.getValue() >> "321"

        when:
        Long id = productService.save(sampleProduct).getId()
        productService.assignValueFromExternalService(id)

        then:
        productService.getById(id) != null
        productService.getById(id).getClientValue() == "321"
    }
}
