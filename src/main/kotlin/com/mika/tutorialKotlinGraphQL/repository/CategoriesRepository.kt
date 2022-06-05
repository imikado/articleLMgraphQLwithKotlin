package com.mika.tutorialKotlinGraphQL.repository

import com.mika.tutorialKotlinGraphQL.graphql.types.entity.Category
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Repository
interface CategoriesRepository : ReactiveCrudRepository<Category, Long> {

    @Query("SELECT * FROM categories")
    override fun findAll(): Flux<Category>

    @Query("SELECT * FROM categories WHERE id = :id")
    override fun findById(id: Long): Mono<Category>



}