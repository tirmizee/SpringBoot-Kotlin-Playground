package com.tirmizee.routers

import com.tirmizee.services.ZipFileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class Handler {

    @Autowired
    private lateinit var zipFileService: ZipFileService

    suspend fun zipWithPassword(request: ServerRequest): ServerResponse {
        return ServerResponse.ok()
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
            .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=compressed.zip")
            .bodyValueAndAwait(zipFileService.generateZipBytes())
    }

}