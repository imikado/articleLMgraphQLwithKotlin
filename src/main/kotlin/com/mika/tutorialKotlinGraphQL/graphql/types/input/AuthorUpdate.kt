package com.mika.tutorialKotlinGraphQL.graphql.types.input

data class AuthorUpdate (
    var id: Long,
    val firstname: String?,
    val lastname: Long?,
)
