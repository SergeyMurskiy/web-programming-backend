package com.murskiy.sergey.webprogramming.config

import org.elasticsearch.client.Client
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.transport.client.PreBuiltTransportClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories
import java.net.InetAddress

@Configuration
@EnableElasticsearchRepositories(basePackages = ["com.murskiy.sergey.webprogramming.repositories"])
open class ElasticsearchConfiguration {
    @Value("\${elasticsearch.cluster.name}")
    private val clusterName: String? = null

    @Value("\${elasticsearch.host}")
    private val host: String = ""

    @Value("\${elasticsearch.port}")
    private val port: Int = 0
    @Bean
    @Throws(Exception::class)
    open fun client(): Client {
        val elasticsearchSettings = Settings.builder()
                .put("client.transport.sniff", true)
                .put("cluster.name", clusterName).build()

        return PreBuiltTransportClient(elasticsearchSettings)
                .addTransportAddress(InetSocketTransportAddress(InetAddress.getByName(host), port))
    }

    @Bean
    @Throws(Exception::class)
    open fun elasticsearchTemplate(): ElasticsearchTemplate {
        return ElasticsearchTemplate(client())
    }
}