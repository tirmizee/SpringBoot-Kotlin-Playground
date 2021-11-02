package com.tirmizee.configuration

import com.jcraft.jsch.ChannelSftp.LsEntry
import com.tirmizee.properties.SftpProperty
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.InboundChannelAdapter
import org.springframework.integration.annotation.Poller
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.core.MessageSource
import org.springframework.integration.file.filters.AcceptOnceFileListFilter
import org.springframework.integration.file.remote.session.CachingSessionFactory
import org.springframework.integration.file.remote.session.SessionFactory
import org.springframework.integration.sftp.filters.SftpSimplePatternFileListFilter
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizer
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizingMessageSource
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory
import java.io.File


@Configuration
class SftpConfiguration(
    private val sftpProperty: SftpProperty
) {

    @Bean
    fun sessionFactory(): SessionFactory<LsEntry?>? {
        val sftpSessionFactory = DefaultSftpSessionFactory(sftpProperty.client.sharedSession)
        sftpSessionFactory.setHost(sftpProperty.client.host)
        sftpSessionFactory.setPort(sftpProperty.client.port)
        sftpSessionFactory.setUser(sftpProperty.client.username)
        sftpSessionFactory.setPassword(sftpProperty.client.password)
        sftpSessionFactory.setTimeout(sftpProperty.client.timeOut)
        sftpSessionFactory.setAllowUnknownKeys(sftpProperty.client.allowUnknowKey)
        return sftpSessionFactory
    }

    @Bean
    fun cachingSessionFactory(sessionFactory: SessionFactory<LsEntry>?): CachingSessionFactory<LsEntry>? {
        val cachingSessionFactory = CachingSessionFactory(sessionFactory)
        cachingSessionFactory.setPoolSize(sftpProperty.client.cache.poolSize)
        cachingSessionFactory.setTestSession(sftpProperty.client.cache.testSession)
        cachingSessionFactory.setSessionWaitTimeout(sftpProperty.client.cache.sessionWaitTimeout)
        return cachingSessionFactory
    }

    @Bean(name = ["sftpInboundFileSynchronizer"])
    fun sftpInboundFileSynchronizer(@Qualifier("cachingSessionFactory") cachingSessionFactory: CachingSessionFactory<LsEntry?>?): SftpInboundFileSynchronizer? {
        val remoteFileSynchronizer = SftpInboundFileSynchronizer(cachingSessionFactory)
        remoteFileSynchronizer.setRemoteDirectory(sftpProperty.inbound.remoteDirectory)
        remoteFileSynchronizer.setDeleteRemoteFiles(sftpProperty.inbound.remoteFilesDelete)
        remoteFileSynchronizer.setFilter(SftpSimplePatternFileListFilter(sftpProperty.inbound.remoteFilesFilter))
        return remoteFileSynchronizer
    }

    @Bean
    @InboundChannelAdapter(channel = "sftpChannel", poller = [Poller(fixedDelay = "3000")])
    fun sftpMessageSource(@Qualifier("sftpInboundFileSynchronizer") sftpInboundFileSynchronizer: SftpInboundFileSynchronizer?): MessageSource<File?>? {
        val localFileSynchronizer = SftpInboundFileSynchronizingMessageSource(sftpInboundFileSynchronizer)
        localFileSynchronizer.setLocalDirectory(File(sftpProperty.inbound.localDirectory))
        localFileSynchronizer.isLoggingEnabled = true
        localFileSynchronizer.setAutoCreateLocalDirectory(sftpProperty.inbound.localDirectoryAutoCreate)
        localFileSynchronizer.setLocalFilter(AcceptOnceFileListFilter())
        return localFileSynchronizer
    }

    @ServiceActivator(inputChannel = "sftpChannel")
    fun handleIncomingFile(file: File) {
        println("file -> ${file.name}", )
    }

}