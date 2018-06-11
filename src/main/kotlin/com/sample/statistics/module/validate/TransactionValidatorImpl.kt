package com.sample.statistics.module.validate

import com.sample.statistics.config.AppConfig.config
import com.sample.statistics.config.AppConfig.offset
import com.sample.statistics.module.store.Transaction
import mu.KLogging
import java.time.Instant

class TransactionValidatorImpl : TransactionValidator, KLogging() {

    val timeDelta by lazy {
        config[offset]
    }

    override fun validate(transaction: Transaction): Boolean {
        val delta = Math.abs(Instant.now().epochSecond - transaction.timestamp)
        logger.debug { "time delta: $delta" }
        return delta < timeDelta
    }
}