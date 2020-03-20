package msa.demo.timeline.service;

import msa.demo.timeline.domain.Comment;

public interface CommentService {

    void writeComment(Comment comment);

    void updateComment(Comment comment);

    void deleteCommentByCommentId(Long commentId);

    // 댓글 조회

}
