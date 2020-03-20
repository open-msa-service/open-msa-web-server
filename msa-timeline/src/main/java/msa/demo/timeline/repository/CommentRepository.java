package msa.demo.timeline.repository;

import msa.demo.timeline.domain.Comment;
import msa.demo.timeline.domain.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    void deleteCommentByTimeId(TimeLine timeLine);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Comment c SET c.content = :#{#com.content} WHERE c.commentId = :#{#com.commentId}")
    void updateComment(@Param("com")Comment comment);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Comment c SET c.profileHref = :profileHref WHERE c.userId = :userId")
    void updateUserProfile(@Param("userId")String userId, @Param("profileHref")String profileHref);

    void deleteCommentByCommentId(Long commentId);

    List<Comment> findCommentByTimeIdOrderByUpdateTimeDesc(TimeLine timeLine);
}