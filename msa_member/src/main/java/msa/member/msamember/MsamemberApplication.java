package msa.member.msamember;

import msa.member.msamember.domain.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileUploadProperties.class
})
public class MsamemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsamemberApplication.class, args);
    }

}
