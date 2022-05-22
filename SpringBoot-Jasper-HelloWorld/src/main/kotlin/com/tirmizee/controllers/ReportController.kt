package com.tirmizee.controllers

import com.tirmizee.services.ReportService
import org.springframework.http.ContentDisposition
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/report")
class ReportController (
    val reportService: ReportService
){

    @GetMapping(path = ["/hello/{date}"], produces = [MediaType.APPLICATION_PDF_VALUE])
    suspend fun helloReport(@PathVariable date: String) : ByteArray? {
        return reportService.generateHelloReport()
    }

    @GetMapping(path = ["/hello2"], produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    suspend fun helloReport2() : ByteArray? {
        return reportService.generateHelloReport()
    }

    @GetMapping(path = ["/hello3"], produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    suspend fun helloReport3() : ResponseEntity<ByteArray> {
        val byteArray = reportService.generateHelloReport()
        val contentDisposition = ContentDisposition.builder("inline")
            .filename("hello3.pdf")
            .build()
        val httpHeaders = HttpHeaders()
        httpHeaders.contentDisposition = contentDisposition
        return ResponseEntity.ok().headers(httpHeaders).body(byteArray)
    }

    @GetMapping(path = ["/hello4"], produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    suspend fun helloReport4() : ResponseEntity<ByteArray> {
        val byteArray = reportService.generateHelloReport()
        val contentDisposition = ContentDisposition.builder("attachment")
            .filename("hello3.pdf")
            .build()
        val httpHeaders = HttpHeaders()
        httpHeaders.contentDisposition = contentDisposition
        return ResponseEntity.ok().headers(httpHeaders).body(byteArray)
    }

}