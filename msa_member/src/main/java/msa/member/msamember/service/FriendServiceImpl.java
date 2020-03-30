package msa.member.msamember.service;


import lombok.extern.slf4j.Slf4j;
import msa.member.msamember.domain.Friend;
import msa.member.msamember.domain.Member;
import msa.member.msamember.domain.State;
import msa.member.msamember.repository.FriendRepository;
import msa.member.msamember.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class FriendServiceImpl implements FriendService{

    private final FriendRepository friendRepository;

    private final MemberRepository memberRepository;

    public FriendServiceImpl(FriendRepository friendRepository, MemberRepository memberRepository) {
        this.friendRepository = friendRepository;
        this.memberRepository = memberRepository;
    }

    /**
     * userId1에 해당하는 친구 목록 가지고 오기
     * @param userId1
     * @return
     */
    @Override
    public Map<String, List<Member>> getMyFriendListByUserId(String userId1) {
        // Friend를 가지고 와서 Member 형태로 보내줘야 한다.
        // 친구 목록
        List<String> friendIds = friendRepository.findUserId1ByUserId2AndState(userId1, State.FRIEND);

        // 친구 목록이 없는 경우 빈 리스트를 넣어준다
        if(friendIds.size() == 0) {
            friendIds.add(" ");
        }
        List<Member> friendMemberInfo = memberRepository.findFriendInfo(friendIds);

        // 수락 대기 중인 친구요청
        List<String> requestFriends = friendRepository.findUserId1ByUserId2AndState(userId1, State.WAIT);
        if(requestFriends.size() == 0){
            requestFriends.add(" ");
        }
        List<Member> requestMemberInfo = memberRepository.findFriendInfo(requestFriends);

        Map<String, List<Member>> responseMap = new HashMap<>();
        responseMap.put("friend", friendMemberInfo);
        responseMap.put("requestFriend", requestMemberInfo);

        return responseMap;
    }

    /**
     * userId1이 userId2에 친구 요청
     * @param userId1 사용자
     * @param userId2 요청 받을 친구
     */
    @Override
    public void requestFriendByUserId(String userId1, String userId2) {
        Friend friend = new Friend(userId1, userId2, State.WAIT);
        int count = friendRepository.countFriendByUserId1AndUserId2(userId1, userId2);
        if(count < 1)
            friendRepository.save(friend);
    }


    /**
     * 친구 요청 수락
     * @param userId1 사용자
     * @param userId2 친구 요청자
     */
    @Override
    public void accpetFriendByUserID(String userId1, String userId2) {
        // 경우의 수를 나누기 위해 확인한다.
        int count = friendRepository.countFriendByUserId1AndUserId2(userId1, userId2);
        Friend friend = new Friend(userId1, userId2, State.FRIEND);
        Friend friend2 = new Friend(userId2, userId1, State.FRIEND);
        // 친구만 요청한 경우
        if(count == 0){
            friendRepository.updateFriend(friend2);
            friendRepository.save(friend);
        }else{ // 둘다 요청한 경우
            friendRepository.updateFriend(friend);
            friendRepository.updateFriend(friend2);
        }

    }

    /**
     * 친구들의 id를 배열로 넘겨준다.
     * @param userId
     * @return
     */
    @Override
    public String[] restClientFriendList(String userId) {
        List<Friend> list = friendRepository.findAllByUserId1AndState(userId, State.FRIEND);
        String[] friendArray = new String[list.size()];
        for(int i=0; i < list.size(); i++){
            String friendId = list.get(i).getUserId2();
            friendArray[i] = friendId;
        }
        return friendArray;
    }

    /**
     * 친구인지 여부를 보내준다.
     * @param userId
     * @param visitId
     * @return
     */
    @Override
    public boolean restClientIsFriend(String userId, String visitId) {
        int count = friendRepository.countFriendByUserId1AndUserId2AndState(userId, visitId, State.FRIEND);
        return count > 0;
    }
}
