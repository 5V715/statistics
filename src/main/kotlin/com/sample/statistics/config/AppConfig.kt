package com.sample.statistics.config

import com.natpryce.konfig.ConfigurationProperties
import com.natpryce.konfig.ConfigurationProperties.Companion.systemProperties
import com.natpryce.konfig.EnvironmentVariables
import com.natpryce.konfig.PropertyGroup
import com.natpryce.konfig.getValue
import com.natpryce.konfig.longType
import com.natpryce.konfig.overriding

object AppConfig : PropertyGroup() {

    val offset  by longType

    val config = systemProperties() overriding
        EnvironmentVariables() overriding
        ConfigurationProperties.fromResource("defaults.properties")


}