package msa.timeline.msatimeline;

import msa.timeline.msatimeline.domain.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableConfigurationProperties({
        FileUploadProperties.class
})
public class MsatimelineApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsatimelineApplication.class, args);
    }

}
