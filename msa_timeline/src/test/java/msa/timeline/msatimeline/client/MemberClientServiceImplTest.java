package msa.timeline.msatimeline.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberClientServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(MemberClientServiceImplTest.class);

    @Autowired
    private RestTemplate restTemplate;

    private final String memberHost = "http://localhost:8081";

    @Test
    void getUserIsFriend(){
        JsonNode jsonNode = restTemplate.getForObject(memberHost + "/friend/isFriend/test/test1", JsonNode.class);
        assert jsonNode != null;
        log.info(jsonNode.toString());
    }

}