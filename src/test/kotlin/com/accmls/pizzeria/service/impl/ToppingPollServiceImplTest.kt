package com.accmls.pizzeria.service.impl

import com.accmls.pizzeria.dto.ToppingsDTO
import com.accmls.pizzeria.entity.Customer
import com.accmls.pizzeria.repository.CustomerRepository
import com.accmls.pizzeria.service.ToppingPollService
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import jakarta.persistence.EntityNotFoundException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class ToppingPollServiceImplTest {

    val customerRepositoryMockk: CustomerRepository = mockk()
    val serviceTest: ToppingPollService = ToppingPollServiceImpl(customerRepositoryMockk)

    @Test
    fun submit_Success() {
        // given
        val customer = slot<Customer>()
        val toppingsDTO = ToppingsDTO("testCustomer1@mail.com", setOf("Pepperoni", "Mushroom", "Extra cheese"))

        every { customerRepositoryMockk.save(capture(customer)) } returns mockk()

        // when
        serviceTest.submit(toppingsDTO)

        // then
        assert(customer.captured.email == "testCustomer1@mail.com")
        assert(customer.captured.toppings.map { it.name }.containsAll(setOf("Pepperoni", "Mushroom", "Extra cheese")))
    }

    @Test
    fun getSubmittedResultsByEmail_EntityNotFoundException() {
        // given
        val email = "testCustomer2@email.com"
        every { customerRepositoryMockk.findById(any()) } returns Optional.empty()

        // when
        val entityNotFoundException = assertThrows<EntityNotFoundException> {
            serviceTest.getSubmittedResultsByEmail(email)
        }

        // then
        assert(entityNotFoundException.message == "No results was found for the customer: $email")
    }
}