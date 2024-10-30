package com.sostisoft.api.customer

import com.sostisoft.api.CustomerRequest
import com.sostisoft.api.CustomerResponse
import com.sostisoft.domain.Customer
import org.springframework.stereotype.Component

@Component
class CustomerControllerMapper {

    fun toCustomerResponse(customer: Customer): CustomerResponse {
        return CustomerResponse().apply {
            id = customer.id.toString()
            name = customer.name
            surname = customer.surname
            photoUrl = customer.photoUrl.toString()
            createdBy = customer.createdBy
            modifiedBy = customer.modifiedBy
        }
    }

    fun toDomain(customerRequest: CustomerRequest): Customer =
        Customer(
            name = customerRequest.name,
            surname = customerRequest.surname,
            photo = customerRequest.photo.contentAsByteArray
        )


}