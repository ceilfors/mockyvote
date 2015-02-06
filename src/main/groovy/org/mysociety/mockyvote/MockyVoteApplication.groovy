package org.mysociety.mockyvote

import groovyx.net.http.RESTClient
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@EnableAutoConfiguration
@ComponentScan
class MockyVoteApplication {

    public static void main(String[] args) {
        SpringApplication.run MockyVoteApplication, args
    }

    @Bean
    @Qualifier('mapit')
    RESTClient mapitRestClient() {
        return new RESTClient('http://mapit.mysociety.org/')
    }

    @Bean
    @Qualifier('yournextmp')
    RESTClient yournextmpRestClient() {
        return new RESTClient('http://yournextmp.popit.mysociety.org/')
    }
}
