package com.mika.tutorialKotlinGraphQL.graphql.query

import com.expediagroup.graphql.annotations.GraphQLDescription
import com.expediagroup.graphql.spring.operations.Query
import com.mika.tutorialKotlinGraphQL.graphql.types.entity.Fruit
import org.springframework.stereotype.Component

@Component
class FruitsQuery : Query {

    var fruitList : List<Fruit> = listOf(
        Fruit(1,"Apple"),
        Fruit(2, "Pear"),
        Fruit(3,"Orange")
    )

    @GraphQLDescription("Return all fruits")
    fun allFruits(): List<Fruit>{
        return fruitList
    }

    @GraphQLDescription("Return a fruit with an id")
    fun fruit(id: Long): Fruit?{
        return fruitList.find { id == it.id }
    }
}