package com.tirmizee.rounters

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class HelloRequest(val name: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class HelloResponse(val name: String)