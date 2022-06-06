package com.mika.tutorialKotlinGraphQL.abstracts

import org.dataloader.DataLoader
import org.dataloader.DataLoaderOptions
import java.util.concurrent.CompletableFuture

abstract class AbstractListLoader<K, E>(
    batchLoader: (Collection<K>) -> Collection<E>,
    keyGetter: (E) ->K,
    optionsInInitializer: (DataLoaderOptions.() -> Unit) ? = null
): DataLoader<K, List<E>>(
    { keys ->
        CompletableFuture.supplyAsync {
            batchLoader(keys)
                .groupBy(keyGetter)
                .let { map ->
                    keys.map { map[it] ?: emptyList() }
                }
        }
    },
    optionsInInitializer?.let {
        DataLoaderOptions().apply {
            this.it()
        }
    }
)