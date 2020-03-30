package msa.member.msamember.service;

import msa.member.msamember.domain.Friend;
import msa.member.msamember.domain.Member;

import java.util.List;
import java.util.Map;

public interface FriendService {

    Map<String, List<Member>> getMyFriendListByUserId(String userId1);

    void requestFriendByUserId(String userId1, String userId2);

    void accpetFriendByUserID(String userId1, String userId2);

    String[] restClientFriendList(String userId);

    boolean restClientIsFriend(String userId, String visitId);

}
