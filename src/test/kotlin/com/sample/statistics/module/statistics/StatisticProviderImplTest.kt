package com.sample.statistics.module.statistics

import com.google.common.truth.Truth.assertThat
import com.sample.statistics.module.store.Transaction
import com.sample.statistics.module.store.TransactionStore
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.time.Instant

class StatisticProviderImplTest {

    @Test
    fun `check if the creation works`() {
        val someTransactions =
            listOf(Transaction(2.0,Instant.now().epochSecond),
                Transaction(5.0,Instant.now().epochSecond),
                Transaction(3.0,Instant.now().epochSecond)
                )
        val store = mock(TransactionStore::class.java)
        `when`(store.getAll()).thenReturn(someTransactions)
        val result = StatisticProviderImpl(transactionStore = store).getStatistic()
        with(result) {
            assertThat(sum).isEqualTo(10.0)
            assertThat(avg).isEqualTo(3.3333333333333335)
            assertThat(count).isEqualTo(3)
            assertThat(min).isEqualTo(2.0)
            assertThat(max).isEqualTo(5.0)
        }
    }

    @Test
    fun `statistics of nothing`() {
        val someTransactions =
            emptyList<Transaction>()
        val store = mock(TransactionStore::class.java)
        `when`(store.getAll()).thenReturn(someTransactions)
        val result = StatisticProviderImpl(transactionStore = store).getStatistic()
        with(result) {
            assertThat(sum).isEqualTo(0.0)
            assertThat(avg).isEqualTo(0.0)
            assertThat(count).isEqualTo(0)
            assertThat(min).isEqualTo(0.0)
            assertThat(max).isEqualTo(0.0)
        }
    }
}