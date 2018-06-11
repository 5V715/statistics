package com.sample.statistics.module.store

import com.sample.statistics.module.validate.TransactionValidator
import org.springframework.beans.factory.annotation.Autowired

class TransactionStoreImpl @Autowired constructor(
    private val transactionValidator: TransactionValidator,
    private var transactions: List<Transaction> = mutableListOf()
) : TransactionStore {

    override fun getAll(): List<Transaction> {
        transactions = transactions.filter { transactionValidator.validate(it) }
        return transactions
    }

    override fun put(transaction: Transaction): Boolean =
        when (transactionValidator.validate(transaction)) {
            true -> {
                transactions += transaction
                true
            }
            else -> false
        }
}