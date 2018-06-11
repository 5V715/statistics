package com.sample.statistics.module.validate

import com.sample.statistics.module.store.Transaction

interface TransactionValidator {

    fun validate(transaction: Transaction): Boolean

}