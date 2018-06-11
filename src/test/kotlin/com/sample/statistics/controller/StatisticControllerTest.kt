package com.sample.statistics.controller

import com.sample.statistics.module.statistics.Statistic
import com.sample.statistics.module.statistics.StatisticProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@WebMvcTest(StatisticController::class)
class StatisticControllerTest {

    @Autowired lateinit var context: WebApplicationContext

    @Test
    fun `test statistic controller`() {
        val mvc = MockMvcBuilders.webAppContextSetup(context).build()
        mvc.perform(
            get("/statistics")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect((status().isOk))
    }

    @TestConfiguration
    class TestConfig {

        @Bean
        fun mockBean(): StatisticProvider {
            val mock = mock(StatisticProvider::class.java)
            `when`(mock.getStatistic())
                .thenReturn(Statistic(0.0, 0.0, 0.0, 0.0, 0))
            return mock
        }
    }
}