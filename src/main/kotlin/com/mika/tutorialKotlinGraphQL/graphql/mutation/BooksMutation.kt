package com.mika.tutorialKotlinGraphQL.graphql.mutation

import com.expediagroup.graphql.spring.operations.Mutation
import com.mika.tutorialKotlinGraphQL.graphql.types.entity.Book
import com.mika.tutorialKotlinGraphQL.graphql.types.input.BookUpdate
import com.mika.tutorialKotlinGraphQL.repository.BooksRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

@Component
class BooksMutation(private val booksRepository: BooksRepository ): Mutation {

    suspend fun insertBook(bookInput : Book): Book? {

        return booksRepository.save(bookInput).awaitSingle()
    }

    suspend fun updateBook(  bookInput : BookUpdate): Book? {

        var bookToUpdate=booksRepository.findById(bookInput.id).awaitSingle()

        for(fieldLoop in bookInput::class.memberProperties){
            val valueToUpdate=fieldLoop?.getter?.call(bookInput)
            if( valueToUpdate != null  ) {

                var fieldToUpdate= bookToUpdate::class.memberProperties.filterIsInstance<KMutableProperty<*>>().find{it.name==fieldLoop.name}
                fieldToUpdate?.setter?.call(bookToUpdate,valueToUpdate)

            }
        }

        return booksRepository.save(bookToUpdate).awaitSingle()

    }


}