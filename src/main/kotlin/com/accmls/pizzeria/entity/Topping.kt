package com.accmls.pizzeria.entity

import jakarta.persistence.*

@Entity
data class Topping(
    @Id
    @Column(length = 20, unique = true, nullable = false)
    val name: String
)