package com.mika.tutorialKotlinGraphQL.graphql.types.entity

import com.mika.tutorialKotlinGraphQL.graphql.query.BooksQuery.AuthorInstance
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("books")
data class Book (
    @Id
    val id: Long?,
    val title: String,
    val author_id: Long,
    val category_id: Long
){

     suspend fun Author(): Author{

        return AuthorInstance.findById( author_id).awaitSingle()

    }
}