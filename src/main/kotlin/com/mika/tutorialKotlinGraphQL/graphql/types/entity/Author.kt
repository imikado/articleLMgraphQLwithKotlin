package com.mika.tutorialKotlinGraphQL.graphql.types.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("authors")
data class Author (
    @Id
    val id: Long?,
    var firstname: String,
    var lastname: String,

) {

}