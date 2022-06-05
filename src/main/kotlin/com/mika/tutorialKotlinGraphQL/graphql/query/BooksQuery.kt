package com.mika.tutorialKotlinGraphQL.graphql.query

import com.expediagroup.graphql.annotations.GraphQLDescription
import com.expediagroup.graphql.spring.operations.Query
import com.mika.tutorialKotlinGraphQL.graphql.types.entity.Author
import com.mika.tutorialKotlinGraphQL.graphql.types.entity.Book
import com.mika.tutorialKotlinGraphQL.repository.AuthorsRepository
import com.mika.tutorialKotlinGraphQL.repository.BooksRepository
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class BooksQuery(private val booksRepository: BooksRepository, authorsRepository: AuthorsRepository) : Query {

    init {
       authorsRepositoryInstance =authorsRepository
    }

    @GraphQLDescription("Return all books")
    suspend fun allBooks(): List<Book>{
        return booksRepository.findAll().collectList().awaitFirst()
    }

    @GraphQLDescription("Return a book by his id")
    suspend fun book(id: Long): Book?{
        return booksRepository.findById(id).awaitSingle()
    }


    companion object AuthorInstance{

       lateinit var authorsRepositoryInstance: AuthorsRepository

        fun findById(id:Long): Mono<Author> {
            return authorsRepositoryInstance.findById(id)
        }
    }

}