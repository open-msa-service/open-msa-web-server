package msa.gateway.msagateway;

import msa.gateway.msagateway.config.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class MsaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaGatewayApplication.class, args);
    }

}
