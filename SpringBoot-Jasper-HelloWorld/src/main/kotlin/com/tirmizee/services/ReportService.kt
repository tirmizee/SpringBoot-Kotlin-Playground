package com.tirmizee.services

import net.sf.jasperreports.engine.JREmptyDataSource
import net.sf.jasperreports.engine.JasperExportManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JasperReport
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.HashMap
import kotlin.coroutines.coroutineContext

@Service
class ReportService(
    @Qualifier("helloJasper") private val helloJasper: JasperReport
) {

    suspend fun generateHelloReport(): ByteArray? {
        val  jasperPrint = JasperFillManager.fillReport(helloJasper, HashMap(), JREmptyDataSource())
        return JasperExportManager.exportReportToPdf(jasperPrint)
    }

}