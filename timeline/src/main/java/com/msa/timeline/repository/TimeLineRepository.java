package com.msa.timeline.repository;

import com.msa.timeline.domain.Scope;
import com.msa.timeline.domain.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface TimeLineRepository extends JpaRepository<TimeLine, Long> {

    List<TimeLine> findByUserIdOrderByUpdateTimeDesc(String userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TimeLine t SET t.content = :#{#time.content}, t.fileLocation = :#{#time.fileLocation}" +
            ", t.scope = :#{#time.scope} WHERE t.timeId = :#{#time.timeId}")
    void updateTimeline(@Param("time")TimeLine timeLine);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TimeLine t SET t.profileHref = :profileHref WHERE t.userId = :userId")
    void updateUserProfile(@Param("userId")String userId, @Param("profileHref")String profileHref);

    List<TimeLine> findByUserIdAndScopeOrderByUpdateTimeDesc(String userId, Scope scope);


    @Query(value = "select distinct t.time_id from timeline t, comments c, likes l where t.user_id in (:user) and (c.user_id in (:user) or " +
            "l.user_id in (:user))", nativeQuery = true)
    List<Long> timeLineIdList(@Param("user") List<String> userId);

    @Query(value = "select t.* from timeline t where t.time_id in (:id) order by t.time_id desc", nativeQuery = true)
    List<TimeLine> timeLineList(@Param("id") List<Long> timeId);

}
