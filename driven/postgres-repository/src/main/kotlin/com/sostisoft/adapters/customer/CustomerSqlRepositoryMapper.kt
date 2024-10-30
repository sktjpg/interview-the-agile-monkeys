package com.sostisoft.adapters.customer

import com.sostisoft.adapters.user.UserEntity
import com.sostisoft.domain.Customer
import org.springframework.stereotype.Component

@Component
class CustomerSqlRepositoryMapper {

    fun toDomain(customerEntity: CustomerEntity): Customer =
        Customer(
            id = customerEntity.id,
            name = customerEntity.name,
            surname = customerEntity.surname,
            photoUrl = customerEntity.photoUrl,
            createdBy = customerEntity.createdBy!!.username,
            modifiedBy = customerEntity.modifiedBy!!.username,
            createdAt = customerEntity.createdAt,
            updatedAt = customerEntity.updatedAt,
        )

    fun toEntity(customer: Customer): CustomerEntity =
        CustomerEntity(
            name = customer.name,
            surname = customer.surname,
            photoUrl = customer.photoUrl,
            createdBy = UserEntity(username = customer.createdBy!!),
            modifiedBy = UserEntity(username = customer.modifiedBy!!),
            createdAt = customer.createdAt,
            updatedAt = customer.updatedAt,
        )

}