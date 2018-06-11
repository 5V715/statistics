package com.sample.statistics.module.validate

import com.google.common.truth.Truth
import com.sample.statistics.module.store.Transaction
import org.junit.Test
import java.time.Instant

class TransactionValidatorImplTest {

    @Test
    fun `transactions is too old`() {
        val validator = TransactionValidatorImpl()
        val someTransaction =
        Transaction(45.9, Instant.now().toEpochMilli() - 10000)

        Truth.assertThat(validator.validate(someTransaction)).isFalse()
    }

    @Test
    fun `transactions is ok`() {
        val validator = TransactionValidatorImpl()
        val someTransaction =
            Transaction(45.9, Instant.now().toEpochMilli() - 5000)

        Truth.assertThat(validator.validate(someTransaction)).isTrue()
    }
}