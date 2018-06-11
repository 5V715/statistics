package com.sample.statistics.module.store

interface TransactionStore {

    fun put(transaction: Transaction): Boolean

    fun getAll(): List<Transaction>
}