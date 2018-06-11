package com.sample.statistics.config

import com.sample.statistics.module.statistics.StatisticProvider
import com.sample.statistics.module.statistics.StatisticProviderImpl
import com.sample.statistics.module.store.TransactionStore
import com.sample.statistics.module.store.TransactionStoreImpl
import com.sample.statistics.module.validate.TransactionValidator
import com.sample.statistics.module.validate.TransactionValidatorImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfig() {

    @Bean
    fun store(transactionValidator: TransactionValidator): TransactionStore =
        TransactionStoreImpl(transactionValidator)

    @Bean
    fun validator(): TransactionValidator = TransactionValidatorImpl()

    @Bean
    fun statisticsProvider(transactionStore: TransactionStore): StatisticProvider =
        StatisticProviderImpl(transactionStore)
}