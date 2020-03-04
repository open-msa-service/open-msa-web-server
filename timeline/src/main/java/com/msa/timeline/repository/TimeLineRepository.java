package com.msa.timeline.repository;

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

}
