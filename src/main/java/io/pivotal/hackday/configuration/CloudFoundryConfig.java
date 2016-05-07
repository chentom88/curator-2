package io.pivotal.hackday.configuration;

import io.pivotal.hackday.domain.CloudFoundryInfo;
import io.pivotal.hackday.domain.OpsManagerInfo;
import io.pivotal.hackday.rest.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pivotal on 5/6/16.
 */

@Configuration
public class CloudFoundryConfig {
    @Autowired
    RestService restService;

    @Bean
    public CloudFoundryInfo cloudFoundryInfo(){
        return restService.getCFInfo();
    }

    @Bean
    public OpsManagerInfo opsManagerInfo(){
        return restService.getOpsManagerInfo();
    }
}
