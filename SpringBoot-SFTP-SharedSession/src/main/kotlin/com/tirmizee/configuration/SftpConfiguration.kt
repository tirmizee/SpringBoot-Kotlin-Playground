package com.tirmizee.configuration

import com.jcraft.jsch.ChannelSftp.LsEntry
import com.tirmizee.properties.SftpProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.file.remote.session.SessionFactory
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory


@Configuration
class SftpConfiguration(
    private val sftpProperty: SftpProperty
) {

    @Bean
    fun sftpSessionFactory(): SessionFactory<LsEntry?>? {
        val sftpSessionFactory = DefaultSftpSessionFactory(sftpProperty.sharedSession)
        sftpSessionFactory.setHost(sftpProperty.host)
        sftpSessionFactory.setPort(sftpProperty.port)
        sftpSessionFactory.setUser(sftpProperty.username)
        sftpSessionFactory.setPassword(sftpProperty.password)
        sftpSessionFactory.setTimeout(sftpProperty.timeOut)
        sftpSessionFactory.setAllowUnknownKeys(sftpProperty.allowUnknowKey)
        return sftpSessionFactory
    }

}