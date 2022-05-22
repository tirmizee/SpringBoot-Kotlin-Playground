package com.tirmizee.configs

import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperReport
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class JasperConfig {

    @Bean
    fun helloJasper(): JasperReport? {
        val jrxmlStream = ClassPathResource("report/hello_world.jrxml").inputStream
        return JasperCompileManager.compileReport(jrxmlStream)
    }

}