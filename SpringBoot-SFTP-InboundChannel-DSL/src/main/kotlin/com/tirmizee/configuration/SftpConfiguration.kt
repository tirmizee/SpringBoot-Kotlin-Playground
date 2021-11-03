package com.tirmizee.configuration

import com.jcraft.jsch.ChannelSftp.LsEntry
import com.tirmizee.properties.SftpProperty
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.dsl.Pollers
import org.springframework.integration.dsl.SourcePollingChannelAdapterSpec
import org.springframework.integration.file.remote.session.CachingSessionFactory
import org.springframework.integration.file.remote.session.SessionFactory
import org.springframework.integration.sftp.dsl.Sftp
import org.springframework.integration.sftp.filters.SftpSimplePatternFileListFilter
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory
import org.springframework.messaging.Message
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

    @Bean
    fun sftpInboundFlow(@Qualifier("cachingSessionFactory") cachingSessionFactory: CachingSessionFactory<LsEntry>): IntegrationFlow? {
        return IntegrationFlows
            .from(
                Sftp.inboundAdapter(cachingSessionFactory)
                    .preserveTimestamp(true)
                    .remoteDirectory(sftpProperty.inbound.remoteDirectory)
                    .deleteRemoteFiles(sftpProperty.inbound.remoteFilesDelete)
                    .filter(SftpSimplePatternFileListFilter(sftpProperty.inbound.remoteFilesFilter))
                    .autoCreateLocalDirectory(sftpProperty.inbound.localDirectoryAutoCreate)
                    .localDirectory(File(sftpProperty.inbound.localDirectory))
            ) { e: SourcePollingChannelAdapterSpec ->
                e.id("sftpInboundAdapter")
                    .autoStartup(true)
                    .poller(Pollers.fixedDelay(3000))
            }
            .handle { message: Message<*> -> println(message.payload) }
            .get()
    }

}