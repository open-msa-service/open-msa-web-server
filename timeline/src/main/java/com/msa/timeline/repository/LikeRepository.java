package com.msa.timeline.repository;

import com.msa.timeline.domain.Comment;
import com.msa.timeline.domain.Like;
import com.msa.timeline.domain.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findByTimeId(TimeLine timeId);

    int countLikeByTimeIdAndUserId(TimeLine timeId, String userId);

    void deleteLikeByTimeIdAndUserId(TimeLine timeId, String userId);

    void deleteLikeByLikeId(long likeId);

    void deleteLikeByTimeId(TimeLine timeId);

}
