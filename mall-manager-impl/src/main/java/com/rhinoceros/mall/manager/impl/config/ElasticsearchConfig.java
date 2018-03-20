package com.rhinoceros.mall.manager.impl.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/12 20:04
 */

@Configuration
public class ElasticsearchConfig {

    @Value("#{esConfig['elasticsearch_cluster_name']}")
    private String clusterName;


    @Bean
    public TransportClient transportClient() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name",clusterName)
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        return client;
    }
}
