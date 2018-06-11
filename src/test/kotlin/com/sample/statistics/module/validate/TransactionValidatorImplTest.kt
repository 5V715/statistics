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
        Transaction(45.9, Instant.now().epochSecond - 10)

        Truth.assertThat(validator.validate(someTransaction)).isFalse()
    }

    @Test
    fun `transactions is ok`() {
        val validator = TransactionValidatorImpl()
        val someTransaction =
            Transaction(45.9, Instant.now().epochSecond - 5)

        Truth.assertThat(validator.validate(someTransaction)).isTrue()
    }
}