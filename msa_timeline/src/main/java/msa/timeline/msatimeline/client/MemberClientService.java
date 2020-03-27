package msa.timeline.msatimeline.client;


import msa.timeline.msatimeline.client.dto.Member;

/**
 *  Member와 통신하는 인터페이스
 */
public interface MemberClientService {

    Member retrieveMemberByUserId(String userId);

    String[] retrieveFriendList(String userId);

    boolean retrieveIsFriend(String userId, String visitId);

}
