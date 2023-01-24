package com.accmls.pizzeria.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class ToppingsDTO(
    @field:NotBlank(message = "toppingsDTO.email must not be blank")
    @field:Email(message = "toppingsDTO.email must be a well-formed email address")
    @field:Size(max = 50)
    val email: String,

    @field:NotEmpty(message = "toppingsDTO.toppings must not be blank")
    @field:Size(min=1, max=20)
    val toppings: Set<String>) {
}