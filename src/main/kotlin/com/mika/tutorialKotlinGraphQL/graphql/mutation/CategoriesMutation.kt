package com.mika.tutorialKotlinGraphQL.graphql.mutation

import com.expediagroup.graphql.spring.operations.Mutation
import com.mika.tutorialKotlinGraphQL.graphql.types.entity.Category

import com.mika.tutorialKotlinGraphQL.repository.CategoriesRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component

@Component
class CategoriesMutation(private val categoriesRepository: CategoriesRepository ): Mutation {

    suspend fun insertCategory(categoryInput : Category): Category? {

        return categoriesRepository.save(categoryInput).awaitSingle()
    }




}