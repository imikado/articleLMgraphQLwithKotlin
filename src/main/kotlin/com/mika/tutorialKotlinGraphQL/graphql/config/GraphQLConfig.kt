package com.mika.tutorialKotlinGraphQL.graphql.config

import com.expediagroup.graphql.spring.execution.DataLoaderRegistryFactory
import com.mika.tutorialKotlinGraphQL.abstracts.AbstractListLoader
import com.mika.tutorialKotlinGraphQL.abstracts.AbstractReferenceLoader
import graphql.schema.DataFetchingEnvironment
import org.dataloader.DataLoader
import org.dataloader.DataLoaderRegistry
import org.springframework.beans.factory.annotation.Lookup
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component

@Configuration
internal abstract class GraphQLConfig {

    @Bean
    open fun dataLoaderRegistryFactory(): DataLoaderRegistryFactory =
        object: DataLoaderRegistryFactory {
            override fun generate(): DataLoaderRegistry = dataLoaderRegistry()
        }

    @Bean
    @Scope(
        ConfigurableBeanFactory.SCOPE_PROTOTYPE,
        proxyMode = ScopedProxyMode.NO
    )
    protected open fun dataLoaderRegistry(
        loaders: List<DataLoader<*, *>>
    ): DataLoaderRegistry =
        DataLoaderRegistry().apply {
            loaders.forEach { loader ->
                register(
                    loader::class.qualifiedName,
                    loader
                )
            }
        }

    @Lookup
    protected abstract fun dataLoaderRegistry(): DataLoaderRegistry
}

inline fun <reified L: AbstractReferenceLoader<K, R>, K, R> DataFetchingEnvironment.getReferenceLoader(
): DataLoader<K, R?> =
    this.getDataLoader<K, R?>(L::class.qualifiedName)


inline fun <reified L: AbstractListLoader<K, E>, K, E> DataFetchingEnvironment.getListLoader(
): DataLoader<K, List<E>> =
    this.getDataLoader<K, List<E>>(L::class.qualifiedName)

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Component
@Scope(
    ConfigurableBeanFactory.SCOPE_PROTOTYPE,
    proxyMode = ScopedProxyMode.NO
)
annotation class DataLoaderComponent