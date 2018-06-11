package com.sample.statistics.module.store

import com.google.common.truth.Truth.assertThat
import com.sample.statistics.module.validate.TransactionValidatorImpl
import org.junit.Test
import java.time.Instant

class TransactionStoreImplTest {

    @Test
    fun `check if the time framing works`() {
        val validator = TransactionValidatorImpl()
        val transactionStore = TransactionStoreImpl(validator)

        transactionStore.put(Transaction(4.4, Instant.now().toEpochMilli()))
        transactionStore.put(Transaction(4.3, Instant.now().toEpochMilli() - 10000))
        transactionStore.put(Transaction(5.4, Instant.now().toEpochMilli()))

        val result = transactionStore.getAll()
        assertThat(result).hasSize(2)
    }

    @Test
    fun `check that add elements validation`() {
        val validator = TransactionValidatorImpl()
        val transactionStore = TransactionStoreImpl(validator)

        transactionStore.put(Transaction(4.4, Instant.now().toEpochMilli()))
        transactionStore.put(Transaction(4.3, Instant.now().toEpochMilli() - 5000))

        val result = transactionStore.getAll()
        assertThat(result).hasSize(2)
        Thread.sleep(5000)
        val laterResult = transactionStore.getAll()
        assertThat(laterResult).hasSize(1)
    }
}