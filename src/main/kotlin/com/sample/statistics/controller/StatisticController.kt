package com.sample.statistics.controller

import com.sample.statistics.module.statistics.StatisticProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.util.MimeTypeUtils
import org.springframework.web.bind.annotation.GetMapping

@Controller
class StatisticController @Autowired constructor(val statisticProvider: StatisticProvider) {

    @GetMapping(path = ["/statistics"],
        produces = [MimeTypeUtils.APPLICATION_JSON_VALUE])
    fun stats() = ResponseEntity(statisticProvider.getStatistic(), HttpStatus.OK)
}