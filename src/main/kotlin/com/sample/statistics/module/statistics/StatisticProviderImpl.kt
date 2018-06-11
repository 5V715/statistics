package com.sample.statistics.module.statistics

import com.sample.statistics.module.store.Transaction
import com.sample.statistics.module.store.TransactionStore
import org.springframework.beans.factory.annotation.Autowired

class StatisticProviderImpl @Autowired constructor(private val transactionStore: TransactionStore) : StatisticProvider {

    override fun getStatistic(): Statistic =
        transactionStore.getAll().getStatistic()

    private fun Collection<Transaction>.getStatistic(): Statistic {
        val sum = sumByDouble { it.amount }
        return Statistic(sum,
            when (size) {
                0 -> 0.0
                else -> sum / size
            },
            maxBy { it.amount }?.amount ?: 0.0,
            minBy { it.amount }?.amount ?: 0.0,
            size.toLong())
    }
}