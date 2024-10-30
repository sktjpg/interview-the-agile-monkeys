package com.sostisoft.usecase

import com.sostisoft.domain.Customer
import com.sostisoft.domain.errors.NotFoundException
import com.sostisoft.ports.repository.CustomerRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CustomerUseCase(
    private val tokenUseCase: TokenValidatorUserCase,
    private val customerRepository: CustomerRepository
) {
    fun findById(id: Long, token: String?): Customer {
        tokenUseCase.validateToken(token, false)

        return id
            .let(customerRepository::findById)
            ?: throw NotFoundException("Customer not found")
    }

    fun findAll(token: String?): List<Customer> {
        tokenUseCase.validateToken(token, false)

        return customerRepository.findAll()
    }

    fun createCustomer(user: Customer, token: String?): Customer =
        tokenUseCase
            .validateToken(token, false)
            .userName
            .let { customer(user, it) }
            .let(customerRepository::createCustomer)

    private fun customer(user: Customer, userName: String?): Customer {
        val now = LocalDateTime.now()
        return user
            .copy(
                createdBy = userName,
                modifiedBy = userName,
                updatedAt = now,
                createdAt = now,
            )
    }

    fun deleteCustomer(id: Long, token: String?) {
        tokenUseCase.validateToken(token, false)

        return customerRepository.deleteCustomer(id)
    }

    fun updateCustomer(id: Long, user: Customer, token: String?): Customer? =
        tokenUseCase
            .validateToken(token, false)
            .userName
            .let { updatedCostumer(user, it) }
            .let { customerRepository.updateCustomer(id, it) }

    private fun updatedCostumer(user: Customer, string: String?): Customer =
        user
            .copy(
                modifiedBy = string,
                updatedAt = LocalDateTime.now(),
            )
}