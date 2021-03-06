package com.spring.boot.journal.config;

import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.spring.boot.journal.repositoryES")
@EnableJpaRepositories(basePackages = "com.spring.boot.journal.repository")
@ComponentScan(basePackages = {"com.spring.boot.journal.entities"})
public class ElasticSearchConfig {
	
    @Bean
    public NodeBuilder nodeBuilder() {
        return new NodeBuilder();
    }
}