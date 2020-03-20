package msa.demo.timeline.service;

import msa.demo.timeline.domain.Comment;
import msa.demo.timeline.domain.TimeLine;

import java.util.List;

public interface CommentService {

    void writeComment(Comment comment);

    void updateComment(Comment comment);

    void deleteCommentByCommentId(Long commentId);

    List<Comment> findCommentListByTimeId(TimeLine timeLine);

}
