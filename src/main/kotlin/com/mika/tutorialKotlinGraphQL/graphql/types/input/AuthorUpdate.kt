package com.mika.tutorialKotlinGraphQL.graphql.types.input

data class AuthorUpdate (
    var id: Long,
    val title: String?,
    val author_id: Long?,
    val category_id: Long?

)