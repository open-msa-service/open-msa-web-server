package com.msa.member.repository;

import com.msa.member.domain.Friend;
import com.msa.member.domain.Member;
import com.msa.member.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    int countFriendByUserId1AndUserId2AndState(String userId1, String userId2, State state);

    List<Friend> findFriendByUserId1AndState(String userId1, State state);

    List<Friend> findFriendByUserId2AndState(String userId2, State state);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Friend f SET f.state = :#{#friend.state} where f.userId1 = :#{#friend.userId1} and f.userId2 = :#{#friend.userId2}")
    void updateFriend(@Param("friend") Friend friend);

    @Query(value = "select user_id2 from friends where user_id1 = :user", nativeQuery = true)
    List<String> findAllFriendByUserId(@Param("user") String userId);

}
