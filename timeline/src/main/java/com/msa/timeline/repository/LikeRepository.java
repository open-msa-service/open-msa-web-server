package com.msa.timeline.repository;

import com.msa.timeline.domain.Comment;
import com.msa.timeline.domain.Like;
import com.msa.timeline.domain.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LikeRepository extends JpaRepository<Like, Long> {

//    long countByUserIdAndTimeId(String userId, TimeLine timeId);

//    long countByUserIdAndCommentId(String userId, Comment commentId);

}
