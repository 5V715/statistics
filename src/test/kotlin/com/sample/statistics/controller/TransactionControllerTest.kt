package com.sample.statistics.controller

import com.nhaarman.mockito_kotlin.any
import com.sample.statistics.module.store.TransactionStore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@WebMvcTest(TransactionController::class)
class TransactionControllerTest {

    @Autowired
    lateinit var context: WebApplicationContext

    @Test
    fun `test posting valid transaction to controller`() {
        val mvc = MockMvcBuilders.webAppContextSetup(context).build()
        mvc.perform(
            post("/transactions")
                .content("""{ "amount": 12.4, "timestamp" : 12345 }""")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect((MockMvcResultMatchers.status().isAccepted))
    }

    @Test
    fun `test posting invalid getting transactions`() {
        val mvc = MockMvcBuilders.webAppContextSetup(context).build()
        mvc.perform(
            post("/transactions")
                .content("""{ "amount": 12.4, "timestamp" : 12345 }""")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect((MockMvcResultMatchers.status().isNoContent))
    }

    @Test
    fun `try posting invalid transactions`() {
        val mvc = MockMvcBuilders.webAppContextSetup(context).build()
        mvc.perform(
            post("/transactions")
                .content("""{  }""")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect((MockMvcResultMatchers.status().isNoContent))
    }

    @TestConfiguration
    class TestConfig {

        @Bean
        fun mockBean(): TransactionStore {
            val mock = Mockito.mock(TransactionStore::class.java)
            `when`(mock.put(any()))
                .thenReturn(false, true)
            return mock
        }
    }
}