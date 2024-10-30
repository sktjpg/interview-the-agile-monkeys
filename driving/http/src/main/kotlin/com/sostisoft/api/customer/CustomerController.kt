package com.sostisoft.api.customer

import com.sostisoft.api.CustomerRequest
import com.sostisoft.api.CustomerResponse
import com.sostisoft.api.CustomersApi
import com.sostisoft.api.interceptor.TokenInterceptor
import com.sostisoft.usecase.CustomerUseCase
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController

@Component
@RestController
class CustomerController(
    private val customerUseCase: CustomerUseCase,
    private val customerControllerMapper: CustomerControllerMapper,
) : CustomersApi {

    override fun createCustomer(customerRequest: CustomerRequest): ResponseEntity<CustomerResponse> =
        customerRequest
            .let(customerControllerMapper::toDomain)
            .let { customerUseCase.createCustomer(it, TokenInterceptor.getToken()) }
            .let(customerControllerMapper::toCustomerResponse)
            .let { ResponseEntity.ok(it) }


    override fun deleteCustomer(customerId: String): ResponseEntity<Void> =
        customerId
            .toLong()
            .let { customerUseCase.deleteCustomer(it, TokenInterceptor.getToken()) }
            .let { ResponseEntity.noContent().build<Void>() }

    override fun getCustomerDetails(customerId: String): ResponseEntity<CustomerResponse> =
        customerId
            .toLong()
            .let { customerUseCase.findById(it, TokenInterceptor.getToken()) }
            .let(customerControllerMapper::toCustomerResponse)
            .let { ResponseEntity.ok(it) }

    override fun listAllCustomers(): ResponseEntity<List<CustomerResponse>> =
        customerUseCase
            .findAll(TokenInterceptor.getToken())
            .map(customerControllerMapper::toCustomerResponse)
            .let { ResponseEntity.ok(it) }

    override fun updateCustomer(customerId: String, customerRequest: CustomerRequest): ResponseEntity<Void> =
        customerRequest
            .let(customerControllerMapper::toDomain)
            .let { customerUseCase.updateCustomer(customerId.toLong(), it, TokenInterceptor.getToken()) }
            .let { ResponseEntity.noContent().build() }
}