package com.sostisoft.usecase

import com.sostisoft.domain.Customer
import com.sostisoft.domain.errors.NotFoundException
import com.sostisoft.ports.repository.CustomerRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.net.URI
import java.time.LocalDateTime

class CustomerUseCaseTest {

    @Mock
    private lateinit var tokenUseCase: TokenValidatorUserCase

    @Mock
    private lateinit var customerRepository: CustomerRepository

    private lateinit var customerUseCase: CustomerUseCase

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        customerUseCase = CustomerUseCase(tokenUseCase, customerRepository)
    }

    @Test
    fun `should return customer when found by id`() {
        val customerId = 1L
        val token = "validToken"
        val expectedCustomer = Customer(
            id = customerId,
            name = "John",
            surname = "Doe",
            photoUrl = URI("https://photo.com/photo.jpg").toURL(),
            createdBy = "creator",
            modifiedBy = "modifier",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        `when`(tokenUseCase.validateToken(token, false)).thenReturn(mock())
        `when`(customerRepository.findById(customerId)).thenReturn(expectedCustomer)

        val result = customerUseCase.findById(customerId, token)

        assertEquals(expectedCustomer, result)
        verify(tokenUseCase).validateToken(token, false)
        verify(customerRepository).findById(customerId)
    }

    @Test
    fun `should throw NotFoundException when customer not found by id`() {
        val customerId = 1L
        val token = "validToken"

        `when`(tokenUseCase.validateToken(token, false)).thenReturn(mock())
        `when`(customerRepository.findById(customerId)).thenReturn(null)

        assertThrows<NotFoundException> {
            customerUseCase.findById(customerId, token)
        }
        verify(tokenUseCase).validateToken(token, false)
        verify(customerRepository).findById(customerId)
    }

    @Test
    fun `should return all customers`() {
        val token = "validToken"
        val customers = listOf(
            Customer(
                1,
                "John",
                "Doe",
                URI("https://photo.com/photo.jpg").toURL(),
                null,
                "creator",
                "modifier",
                LocalDateTime.now(),
                LocalDateTime.now()
            ),
            Customer(
                2,
                "Jane",
                "Smith",
                URI("https://photo.com/photo.jpg").toURL(),
                null,
                "creator",
                "modifier",
                LocalDateTime.now(),
                LocalDateTime.now()
            )
        )

        `when`(tokenUseCase.validateToken(token, false)).thenReturn(mock())
        `when`(customerRepository.findAll()).thenReturn(customers)

        val result = customerUseCase.findAll(token)

        assertEquals(customers, result)
        verify(tokenUseCase).validateToken(token, false)
        verify(customerRepository).findAll()
    }

    @Test
    fun `should delete customer by id`() {
        val customerId = 1L
        val token = "validToken"

        `when`(tokenUseCase.validateToken(token, false)).thenReturn(mock())
        doNothing().`when`(customerRepository).deleteCustomer(customerId)

        customerUseCase.deleteCustomer(customerId, token)

        verify(tokenUseCase).validateToken(token, false)
        verify(customerRepository).deleteCustomer(customerId)
    }
}
