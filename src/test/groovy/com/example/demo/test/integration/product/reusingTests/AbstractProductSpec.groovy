package com.example.demo.test.integration.product.reusingTests


import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.data.Product
import com.example.demo.test.integration.product.http.server.ErrorDTO
import spock.lang.Specification

abstract class AbstractProductSpec extends Specification implements SampleProducts {

    def "should save product"() {
        when:
        Long id = saveProduct(sampleProduct).getId()

        then:
        getProduct(id) != null
    }

    def "should post invalid product and get error dto"() {
        when:
        ErrorDTO errorDTO = saveInvalidProduct(invalidProduct)

        then:
        errorDTO.getMessage() == "integer value too high"
    }

    // verify event was sent

    abstract Product saveProduct(Product product)

    abstract Product getProduct(long id)

    abstract ErrorDTO saveInvalidProduct(Product product)
}
