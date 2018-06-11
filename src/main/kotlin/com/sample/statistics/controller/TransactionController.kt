package com.sample.statistics.controller

import com.sample.statistics.module.store.Transaction
import com.sample.statistics.module.store.TransactionStore
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.util.MimeTypeUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus

@Controller
class TransactionController @Autowired constructor(val transactionStore: TransactionStore) : KLogging() {

    @PostMapping(path = ["/transactions"], consumes = [MimeTypeUtils.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun storeTransaction(@RequestBody transaction: Transaction) {
        when (transactionStore.put(transaction)) {
            true -> logger.debug { "stored transaction" }
            else -> {
                logger.debug { "transaction rejected" }
                throw NotAcceptableException()
            }
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    class NotAcceptableException : RuntimeException()
}