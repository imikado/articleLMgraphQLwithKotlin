package com.mika.tutorialKotlinGraphQL.repository

import com.mika.tutorialKotlinGraphQL.graphql.types.entity.Book
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Repository
interface BooksRepository : ReactiveCrudRepository<Book, Long> {

    @Query("SELECT * FROM books")
    override fun findAll(): Flux<Book>

    @Query("SELECT * FROM books WHERE id = :id")
    override fun findById(id: Long): Mono<Book>



}