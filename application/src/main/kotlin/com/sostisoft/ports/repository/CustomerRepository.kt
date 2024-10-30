package com.sostisoft.ports.repository

import com.sostisoft.domain.Customer

interface CustomerRepository {

    fun findById(id: Long): Customer?

    fun findAll(): List<Customer>

    fun createCustomer(user: Customer): Customer

    fun deleteCustomer(id: Long)

    fun updateCustomer(id: Long, user: Customer): Customer?

}