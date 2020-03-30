package msa.member.msamember.repository;

import msa.member.msamember.domain.Friend;
import msa.member.msamember.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findAllByUserId1AndState(String userId1, State state);

    List<Friend> findAllByUserId2AndState(String userId1, State state);

    @Transactional
    @Modifying
    @Query(value = "select f.userId1 from Friend f where f.userId2=:#{#userId2} and f.state = :#{#state}")
    List<String> findUserId1ByUserId2AndState(@Param("userId2") String userId2, @Param("state") State state);

    int countFriendByUserId1AndUserId2(String userId1, String userId2);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Friend f SET f.state = :#{#friend.state} where f.userId1=:#{#friend.userId1} and f.userId2=:#{#friend.userId2}")
    void updateFriend(@Param("friend")Friend friend);

    int countFriendByUserId1AndUserId2AndState(String userId1, String userId2, State state);

}
