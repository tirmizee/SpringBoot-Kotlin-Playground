package com.tirmizee.config

import com.tirmizee.stream.StreamChannels
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.context.annotation.Configuration

@Configuration
@EnableBinding(StreamChannels::class)
class KafkaStreamConfig
