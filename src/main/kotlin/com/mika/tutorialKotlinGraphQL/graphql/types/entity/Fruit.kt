package com.mika.tutorialKotlinGraphQL.graphql.types.entity

import com.expediagroup.graphql.annotations.GraphQLDescription

@GraphQLDescription("A simple Fruit")
data class Fruit(
    val id: Long,
    val name: String
)