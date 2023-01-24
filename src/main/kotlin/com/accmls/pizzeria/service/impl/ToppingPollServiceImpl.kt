package com.accmls.pizzeria.service.impl

import com.accmls.pizzeria.dto.ToppingsDTO
import com.accmls.pizzeria.dto.ToppingsResultDTO
import com.accmls.pizzeria.entity.Customer
import com.accmls.pizzeria.entity.Topping
import com.accmls.pizzeria.repository.CustomerRepository
import com.accmls.pizzeria.service.ToppingPollService
import jakarta.persistence.EntityNotFoundException
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ToppingPollServiceImpl(val customerRepository: CustomerRepository): ToppingPollService {

    val logger = KotlinLogging.logger {}

    @Transactional
    override fun submit(toppingsDTO: ToppingsDTO) {
        logger.info { "Customer ${toppingsDTO.email} submits: ${toppingsDTO.toppings}" }

        val toppings = toppingsDTO.toppings.mapTo(HashSet()) { Topping(it) }
        val customer = Customer(toppingsDTO.email)
        customer.toppings = toppings

        customerRepository.save(customer)
    }

    @Transactional(readOnly = true)
    override fun getAllSubmittedResults(): ToppingsResultDTO {
        logger.info { "Getting all submitted toppings poll results" }

        val toppingPollResults = customerRepository.findAll()
            .flatMap { it.toppings }.groupingBy { it.name }.eachCount()

        return ToppingsResultDTO(toppingPollResults)
    }

    @Transactional(readOnly = true)
    override fun getSubmittedResultsByEmail(email: String): ToppingsDTO {
        logger.info { "Getting submitted toppings poll results for customer: $email" }

        val toppingResults = customerRepository.findById(email)
            .orElseThrow { EntityNotFoundException("No results was found for the customer: $email") }

        return ToppingsDTO(toppingResults.email, toppingResults.toppings.mapTo(HashSet()) {it.name} )
    }

}