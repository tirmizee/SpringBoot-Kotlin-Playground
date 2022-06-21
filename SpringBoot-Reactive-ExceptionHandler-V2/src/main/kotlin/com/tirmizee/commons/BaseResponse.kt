package com.tirmizee.commons

import com.fasterxml.jackson.annotation.JsonProperty

open class BaseResponse(
    @JsonProperty("statusCd")
    val statusCd: String? = "",
    @JsonProperty("statusDesc")
    val statusDesc: String? = ""
)
