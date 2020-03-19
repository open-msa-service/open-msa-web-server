package msa.demo.member;

import msa.demo.member.domain.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileUploadProperties.class
})
public class MsaMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaMemberApplication.class, args);
    }

}
