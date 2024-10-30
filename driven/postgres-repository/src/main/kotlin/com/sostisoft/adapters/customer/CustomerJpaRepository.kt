package com.sostisoft.adapters.customer

import com.sostisoft.adapters.user.UserEntity
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.net.URL
import java.time.LocalDateTime

@Repository
interface CustomerJpaRepository : JpaRepository<CustomerEntity, Long> {


    @Modifying
    @Transactional
    @Query(
        """
        UPDATE CustomerEntity c 
        SET c.name = :name, 
            c.surname = :surname, 
            c.photoUrl = :photoUrl, 
            c.updatedAt = :updatedAt, 
            c.modifiedBy = :modifiedBy 
        WHERE c.id = :id
        """
    )
    fun updateById(
        @Param("id") id: Long,
        @Param("name") name: String,
        @Param("surname") surname: String,
        @Param("photoUrl") photoUrl: URL?,
        @Param("updatedAt") updatedAt: LocalDateTime,
        @Param("modifiedBy") modifiedBy: UserEntity?
    ): CustomerEntity?

}