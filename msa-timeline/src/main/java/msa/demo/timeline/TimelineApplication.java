package msa.demo.timeline;

import msa.demo.timeline.domain.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileUploadProperties.class
})
public class TimelineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimelineApplication.class, args);
    }

}
