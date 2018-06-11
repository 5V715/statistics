package com.sample.statistics.controller

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.time.Instant
import java.util.Random
import java.util.concurrent.ThreadLocalRandom

@RunWith(SpringRunner::class)
@SpringBootTest
class LoadTest {

    @Autowired
    lateinit var context: WebApplicationContext

    @Test
    fun `test posting valid transaction to controller`() {
        val mvc = MockMvcBuilders.webAppContextSetup(context).build()

        for (i in 0..10000) {
            mvc.perform(
                MockMvcRequestBuilders.post("/transactions")
                    .content("{ \"amount\": ${Math.abs(Random().nextDouble())}, " +
                        "\"timestamp\" : ${Instant.now().epochSecond -
                            ThreadLocalRandom.current().nextInt(0, 10 + 1)} }")
                    .contentType(MediaType.APPLICATION_JSON))
        }

        mvc.perform(
            MockMvcRequestBuilders.get("/statistics")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect((MockMvcResultMatchers.status().isOk))
            .andDo { result -> println(result.response.contentAsString) }

        Thread.sleep(1000)

        mvc.perform(
            MockMvcRequestBuilders.get("/statistics")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect((MockMvcResultMatchers.status().isOk))
            .andDo { result -> println(result.response.contentAsString) }

        Thread.sleep(500)

        mvc.perform(
            MockMvcRequestBuilders.get("/statistics")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect((MockMvcResultMatchers.status().isOk))
            .andDo { result -> println(result.response.contentAsString) }
    }
}