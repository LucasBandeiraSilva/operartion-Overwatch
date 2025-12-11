package com.overwatch.agents.infrastructure.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.overwatch.agents.infrastructure.client")
public class AgentsClient {
}
