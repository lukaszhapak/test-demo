package com.example.demo.test.integration.product.reusingTests

import com.example.demo.test.integration.product.data.Product
import com.example.demo.test.integration.product.http.server.ErrorDTO
import com.example.demo.test.integration.product.service.ProductService
import com.example.demo.test.integration.product.service.ValidationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable

@SpringBootTest
class UnitProductSpec extends AbstractAdvancedProductSpec {

    // lets pretend this is unit test

    @Autowired
    ProductService productService

    @Override
    Product saveProduct(Product product) {
        productService.save(product)
    }

    @Override
    Product getProduct(long id) {
        productService.getById(id)
    }

    @Override
    ErrorDTO saveInvalidProduct(Product product) {
        new ErrorDTO(catchThrowable(() -> productService.save(product)) as ValidationException)
    }
}
