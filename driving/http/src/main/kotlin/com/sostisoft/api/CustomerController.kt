package com.sostisoft.api

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController

@Component
@RestController
class CustomerController: CustomersApi {

    override fun createCustomer(customerRequest: CustomerRequest): ResponseEntity<CustomerResponse> {
        return super.createCustomer(customerRequest)
    }

    override fun deleteCustomer(customerId: String): ResponseEntity<Void> {
        return super.deleteCustomer(customerId)
    }

    override fun getCustomerDetails(customerId: String): ResponseEntity<CustomerResponse> {
        return super.getCustomerDetails(customerId)
    }

    override fun listAllCustomers(): ResponseEntity<List<CustomerResponse>> {
        return super.listAllCustomers()
    }

    override fun updateCustomer(customerId: String, customerRequest: CustomerRequest): ResponseEntity<Void> {
        return super.updateCustomer(customerId, customerRequest)
    }
}