package com.accmls.pizzeria.controller

import com.accmls.pizzeria.dto.ToppingsDTO
import com.accmls.pizzeria.dto.ToppingsResultDTO
import com.accmls.pizzeria.service.ToppingPollService
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/toppings")
@Validated
class ToppingPollController(val toppingPollService: ToppingPollService) {

    @PostMapping
    fun submit(@RequestBody @Valid toppings: ToppingsDTO) {
        toppingPollService.submit(toppings)
    }

    @GetMapping
    fun getAllSubmittedResults(): ToppingsResultDTO =
        toppingPollService.getAllSubmittedResults()

    @GetMapping("/{email}")
    fun getSubmittedResultsByEmail(@Email @PathVariable email: String): ToppingsDTO =
        toppingPollService.getSubmittedResultsByEmail(email)

}