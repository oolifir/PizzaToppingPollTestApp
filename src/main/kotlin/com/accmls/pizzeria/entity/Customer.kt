package com.accmls.pizzeria.entity

import jakarta.persistence.*

@Entity
data class Customer(
    @Id
    @Column(length = 50, unique = true, nullable = false)
    var email: String
) {

    @ManyToMany(cascade = [CascadeType.ALL])
    var toppings: Set<Topping> = setOf()
}
