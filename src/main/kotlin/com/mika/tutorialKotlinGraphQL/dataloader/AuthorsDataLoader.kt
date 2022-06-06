package com.mika.tutorialKotlinGraphQL.dataloader

import com.mika.tutorialKotlinGraphQL.abstracts.AbstractReferenceLoader
import com.mika.tutorialKotlinGraphQL.graphql.config.DataLoaderComponent
import com.mika.tutorialKotlinGraphQL.graphql.types.entity.Author
import com.mika.tutorialKotlinGraphQL.repository.AuthorsRepository


@DataLoaderComponent
open class AuthorsDataLoader(
    private val authorsRepository: AuthorsRepository
): AbstractReferenceLoader<Long, Author>(
    { authorsRepository.findListByIdList(it.toList()).collectList().block()!!.toMutableList() },
    { it.id!! },
    { setMaxBatchSize(256) }
)