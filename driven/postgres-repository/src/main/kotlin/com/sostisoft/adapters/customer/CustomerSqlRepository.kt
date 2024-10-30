package com.sostisoft.adapters.customer

import com.sostisoft.adapters.user.UserEntity
import com.sostisoft.domain.Customer
import com.sostisoft.ports.repository.CustomerRepository
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class CustomerSqlRepository(
    private val customerJpaRepository: CustomerJpaRepository,
    private val customerJpaRepositoryMapper: CustomerSqlRepositoryMapper,
) : CustomerRepository {

    override fun findById(id: Long): Customer? =
        id
            .let(customerJpaRepository::findById)
            .getOrNull()
            ?.let(customerJpaRepositoryMapper::toDomain)

    override fun findAll(): List<Customer> =
        customerJpaRepository
            .findAll()
            .map(customerJpaRepositoryMapper::toDomain)

    override fun createCustomer(user: Customer): Customer =
        user
            .let(customerJpaRepositoryMapper::toEntity)
            .let(customerJpaRepository::save)
            .let(customerJpaRepositoryMapper::toDomain)

    override fun deleteCustomer(id: Long) {
        customerJpaRepository.deleteById(id)
    }

    override fun updateCustomer(id: Long, customer: Customer): Customer? =
        customerJpaRepository
            .updateById(
                id,
                customer.name,
                customer.surname,
                customer.photoUrl,
                customer.updatedAt,
                UserEntity(username = customer.modifiedBy!!)
            )
            ?.let(customerJpaRepositoryMapper::toDomain)
}