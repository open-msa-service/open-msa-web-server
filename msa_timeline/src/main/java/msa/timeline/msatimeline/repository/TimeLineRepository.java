package msa.timeline.msatimeline.repository;

import msa.timeline.msatimeline.domain.Scope;
import msa.timeline.msatimeline.domain.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TimeLineRepository extends JpaRepository<TimeLine, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE TimeLine t SET t.content = :#{#time.content}, t.fileLocation = :#{#time.fileLocation}" +
            ", t.scope = :#{#time.scope} WHERE t.timeId = :#{#time.timeId}")
    void updateTimeline(@Param("time") TimeLine timeLine);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TimeLine t SET t.profileHref = :profileHref WHERE t.userId = :userId")
    void updateUserProfile(@Param("userId") String userId, @Param("profileHref") String profileHref);

    void deleteTimeLineByTimeId(Long timeId);

    List<TimeLine> findTimeLineByUserIdOrderByUpdateTimeDesc(String userId);

    List<TimeLine> findTimeLineByUserIdAndScopeOrderByUpdateTime(String userId, Scope scope);

    @Query(value = "select distinct t.time_id from timeline t where t.user_id in (:user)", nativeQuery = true)
    List<Long> timeLineIdList(@Param("user") List<String> userId);

    @Query(value = "select t.* from timeline t where t.time_id in (:timeId) order by t.update_time desc", nativeQuery = true)
    List<TimeLine> findTimeLineByTimeIdInOrderByUpdateTime(@Param("timeId") List<Long> timeId);

}
