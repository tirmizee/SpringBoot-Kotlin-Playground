package com.tirmizee.services

import com.tirmizee.rounters.HelloRequest
import com.tirmizee.rounters.HelloResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class TestLogService {

    private val logger = LoggerFactory.getLogger(TestLogService::class.java)

    suspend fun testLogLevel(request: HelloRequest): HelloResponse {
        logger.error("Error message");
        logger.warn("Warning message");
        logger.info("Info message");
        logger.debug("Debug message");
        logger.trace("Trace message");
        return HelloResponse(request.name)
    }

}