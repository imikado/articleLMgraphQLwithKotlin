package com.mika.tutorialKotlinGraphQL.abstracts

import org.dataloader.DataLoader
import org.dataloader.DataLoaderOptions
import java.util.concurrent.CompletableFuture

abstract class AbstractReferenceLoader<K, R: Any> (
    batchLoader: (Collection<K>) -> Collection<R>,
    keyGetter: (R) ->K,
    optionsInInitializer: (DataLoaderOptions.() -> Unit) ? = null
): DataLoader<K, R?>(
    { keys ->
        CompletableFuture.supplyAsync {
            batchLoader(keys)
                .associateBy(keyGetter)
                .let { map ->
                    keys.map { map[it] }
                }
        }
    },
    optionsInInitializer?.let {
        DataLoaderOptions().apply {
            this.it()
        }
    }
) {
}