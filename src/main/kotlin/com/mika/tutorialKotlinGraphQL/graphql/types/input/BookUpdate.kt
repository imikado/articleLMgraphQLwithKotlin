package com.mika.tutorialKotlinGraphQL.graphql.types.input

data class BookUpdate (
    var id: Long,
    var firstname: String?,
    var lastname: String?,

)