package com.sample.statistics.module.validate

import com.sample.statistics.config.AppConfig.config
import com.sample.statistics.config.AppConfig.offsetMilli
import com.sample.statistics.module.store.Transaction
import mu.KLogging
import java.time.Instant

class TransactionValidatorImpl : TransactionValidator, KLogging() {

    private val timeDelta by lazy {
        config[offsetMilli]
    }

    override fun validate(transaction: Transaction): Boolean {
        val delta = Math.abs(Instant.now().toEpochMilli() - transaction.timestamp)
        logger.debug { "time delta: $delta" }
        return delta < timeDelta
    }
}