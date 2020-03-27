package msa.timeline.msatimeline.client;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import msa.timeline.msatimeline.client.dto.Member;
import msa.timeline.msatimeline.client.dto.UserRole;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Member 마이크로 서비스로 부터 오는 데이터 역직렬화
 */
@Slf4j
public class MemberResultDeserializer extends JsonDeserializer<Member> {

    @Override
    public Member deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode jsonNode = objectCodec.readTree(jsonParser);

        log.info("Member Deserializer :::: {}", jsonNode.toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(jsonNode.get("data").get("updateTime").asText(), formatter);
        return new Member(jsonNode.get("data").get("userId").asText(), jsonNode.get("data").get("username").asText(), "", jsonNode.get("data").get("socialId").asText(),
                jsonNode.get("data").get("profileHref").asText(), UserRole.ROLE_USER, jsonNode.get("data").get("email").asText(), jsonNode.get("data").get("phoneNumber").asText(),
                jsonNode.get("data").get("statusMessage").asText(), jsonNode.get("data").get("introduceMessage").asText(), dateTime);
    }
}
