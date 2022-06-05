package com.mika.tutorialKotlinGraphQL.repository

import com.mika.tutorialKotlinGraphQL.graphql.types.entity.Author
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Repository
interface AuthorsRepository : ReactiveCrudRepository<Author, Long> {

    @Query("SELECT id, firstname, lastname FROM authors")
    override fun findAll(): Flux<Author>

    @Query("SELECT id, firstname, lastname FROM authors WHERE id = :id")
    override fun findById(id: Long): Mono<Author>



}