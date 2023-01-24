package com.accmls.pizzeria.service

import com.accmls.pizzeria.dto.ToppingsDTO
import com.accmls.pizzeria.dto.ToppingsResultDTO

interface ToppingPollService {

    fun submit(toppingsDTO: ToppingsDTO)

    fun getAllSubmittedResults(): ToppingsResultDTO

    fun getSubmittedResultsByEmail(email: String): ToppingsDTO
}