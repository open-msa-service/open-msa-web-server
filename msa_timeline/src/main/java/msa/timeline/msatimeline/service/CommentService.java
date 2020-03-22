package msa.timeline.msatimeline.service;

import msa.timeline.msatimeline.domain.Comment;
import msa.timeline.msatimeline.domain.TimeLine;

import java.util.List;

public interface CommentService {

    void writeComment(Comment comment);

    void updateComment(Comment comment);

    void deleteCommentByCommentId(Long commentId);

    List<Comment> findCommentListByTimeId(TimeLine timeLine);

}
