package com.mika.tutorialKotlinGraphQL.graphql.query

import com.expediagroup.graphql.annotations.GraphQLDescription
import com.expediagroup.graphql.spring.operations.Query
import com.mika.tutorialKotlinGraphQL.graphql.types.entity.Author
import com.mika.tutorialKotlinGraphQL.repository.AuthorsRepository
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.stereotype.Component

@Component
class AuthorsQuery(private val authorsRepository: AuthorsRepository) : Query {

    @GraphQLDescription("Return all authors")
    suspend fun allAuthors(): List<Author>{
        return authorsRepository.findAll().collectList().awaitFirst()
    }

    @GraphQLDescription("Return an author by his id")
    suspend fun author(id: Long): Author?{
        return authorsRepository.findById(id).awaitSingle()
    }
}