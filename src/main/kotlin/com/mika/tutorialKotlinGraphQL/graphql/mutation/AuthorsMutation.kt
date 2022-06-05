package com.mika.tutorialKotlinGraphQL.graphql.mutation

import com.expediagroup.graphql.spring.operations.Mutation
import com.mika.tutorialKotlinGraphQL.graphql.types.entity.Author
import com.mika.tutorialKotlinGraphQL.graphql.types.input.AuthorUpdate
import com.mika.tutorialKotlinGraphQL.repository.AuthorsRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

@Component
class AuthorsMutation(private val authorsRepository: AuthorsRepository ): Mutation {

    suspend fun insertAuthor(authorInput : Author): Author? {

        return authorsRepository.save(authorInput).awaitSingle()
    }

    suspend fun updateAuthor(  authorInput : AuthorUpdate): Author? {

        var authorToUpdate=authorsRepository.findById(authorInput.id).awaitSingle()

        for(fieldLoop in authorInput::class.memberProperties){
            val valueToUpdate=fieldLoop?.getter?.call(authorInput)
            if( valueToUpdate != null  ) {

                var fieldToUpdate= authorToUpdate::class.memberProperties.filterIsInstance<KMutableProperty<*>>().find{it.name==fieldLoop.name}
                fieldToUpdate?.setter?.call(authorToUpdate,valueToUpdate)

            }
        }

        return authorsRepository.save(authorToUpdate).awaitSingle()

    }


}