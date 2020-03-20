package msa.demo.timeline.repository;

import msa.demo.timeline.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    void deleteCommentByCommentId(Long commentId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Comment c SET c.content = :#{#com.content} WHERE c.commentId = :#{#com.commentId}")
    void updateComment(@Param("com")Comment comment);

}