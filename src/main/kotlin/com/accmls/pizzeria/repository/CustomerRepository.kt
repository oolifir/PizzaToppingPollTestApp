package com.accmls.pizzeria.repository

import com.accmls.pizzeria.entity.Customer
import org.springframework.data.repository.CrudRepository

interface CustomerRepository: CrudRepository<Customer, String> {
}