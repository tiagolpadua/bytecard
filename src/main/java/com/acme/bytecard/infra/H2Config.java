package com.acme.bytecard.infra;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2Config {

    @Value("${h2Server}")
    private boolean h2Server;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws java.sql.SQLException {
        if(h2Server) {
            return Server.createWebServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
        } else {
            return null;
        }

    }
}
