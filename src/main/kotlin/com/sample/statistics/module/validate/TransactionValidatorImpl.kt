package com.sample.statistics.module.validate

import com.sample.statistics.module.store.Transaction
import mu.KLogging
import java.time.Instant

class TransactionValidatorImpl : TransactionValidator {

    companion object : KLogging() {
        const val OFFSET = 60L
    }

    override fun validate(transaction: Transaction): Boolean {
        val delta = Math.abs(Instant.now().epochSecond - transaction.timestamp)
        logger.debug { "time delta: $delta" }
        return delta <= OFFSET
    }
}