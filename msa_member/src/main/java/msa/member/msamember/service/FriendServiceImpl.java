package msa.member.msamember.service;


import lombok.extern.slf4j.Slf4j;
import msa.member.msamember.domain.Friend;
import msa.member.msamember.domain.State;
import msa.member.msamember.repository.FriendRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class FriendServiceImpl implements FriendService{

    private final FriendRepository friendRepository;

    public FriendServiceImpl(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    /**
     * userId1에 해당하는 친구 목록 가지고 오기
     * @param userId1
     * @return
     */
    @Override
    public Map<String, List<Friend>> getMyFriendListByUserId(String userId1) {
        // 친구 목록
        List<Friend> friends = friendRepository.findAllByUserId1AndState(userId1, State.FRIEND);

        // 수락 대기 중인 친구요청
        List<Friend> requestFriends = friendRepository.findAllByUserId2AndState(userId1, State.WAIT);

        Map<String, List<Friend>> responseMap = new HashMap<>();
        responseMap.put("friend", friends);
        responseMap.put("requestFriend", requestFriends);

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
