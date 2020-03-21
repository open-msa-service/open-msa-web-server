package msa.demo.timeline;

import msa.demo.timeline.domain.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableConfigurationProperties({
        FileUploadProperties.class
})
public class MsaTimelineApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaTimelineApplication.class, args);
    }

}
