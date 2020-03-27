package msa.timeline.msatimeline.client;


import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import msa.timeline.msatimeline.client.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Member 마이크로 서비스와 연결하는 REST 구현체
 */
@Component
public class MemberClientServiceImpl implements MemberClientService{

    private final RestTemplate restTemplate;
    private final String memberHost;


    @Autowired
    public MemberClientServiceImpl(final RestTemplate restTemplate, @Value("${memberHost}")final String memberHost) {
        this.restTemplate = restTemplate;
        this.memberHost = memberHost;
    }

    /**
     * 해당 userId의 사용자 정보를 가지고 온다.
     * @param userId
     * @return
     */
    @Override
    public Member retrieveMemberByUserId(String userId) {
       return restTemplate.getForObject(memberHost + "/member/" + userId,
                Member.class);
    }

    /**
     * 해당 userId의 친구목록을 가지고 온다.
     * @param userId
     * @return
     */
    @Override
    public String[] retrieveFriendList(String userId) {
        return restTemplate.getForObject(memberHost + "/friend/list/" + userId, String[].class);
    }

    /**
     * userId(사용자)와 visitId가 친구인지 확인한다.
     * @param userId
     * @return
     */
    @Override
    public boolean retrieveIsFriend(String userId, String visitId) {
        return restTemplate.getForObject(memberHost + "/friend/isFriend/" + userId + "/" + visitId, Boolean.class);
    }
}
