package com.mika.tutorialKotlinGraphQL.graphql.types.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import reactor.core.publisher.Mono

@Table("books")
data class Book (
    @Id
    val id: Long?,
    val title: String,
    val author_id: Long,
    val category_id: Long
){
 
}