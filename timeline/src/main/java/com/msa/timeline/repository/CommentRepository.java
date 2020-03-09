package com.msa.timeline.repository;

import com.msa.timeline.domain.Comment;
import com.msa.timeline.domain.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Comment c SET c.profileHref = :profileHref WHERE c.userId = :userId")
    void updateUserProfile(@Param("userId")String userId, @Param("profileHref")String profileHref);

    void deleteCommentByTimeId(TimeLine timeId);

}
