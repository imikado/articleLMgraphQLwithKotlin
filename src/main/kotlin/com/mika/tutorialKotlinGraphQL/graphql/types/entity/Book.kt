package com.mika.tutorialKotlinGraphQL.graphql.types.entity

import com.mika.tutorialKotlinGraphQL.dataloader.AuthorsDataLoader
import com.mika.tutorialKotlinGraphQL.graphql.config.getReferenceLoader
import com.mika.tutorialKotlinGraphQL.graphql.query.BooksQuery.AuthorInstance
import graphql.schema.DataFetchingEnvironment
import kotlinx.coroutines.future.await
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

    suspend fun AuthorWithDataLoader(env: DataFetchingEnvironment): Author?{

        return env
            .getReferenceLoader<AuthorsDataLoader, Long, Author>()
            .load(author_id)
            .await()
    }
}