package com.sample.statistics.module.store

import com.sample.statistics.module.validate.TransactionValidator
import org.springframework.beans.factory.annotation.Autowired

class TransactionStoreImpl @Autowired constructor(private val transactionValidator: TransactionValidator, private val transactions: MutableList<Transaction> = mutableListOf()) : TransactionStore {

    override fun getAll(): List<Transaction> =
        transactions.filter { transactionValidator.validate(it) }

    override fun put(transaction: Transaction): Boolean =
        when (transactionValidator.validate(transaction)) {
            true -> transactions.add(transaction)
            else -> false
        }
}