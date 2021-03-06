package msa.timeline.msatimeline.service;

import msa.timeline.msatimeline.domain.Comment;
import msa.timeline.msatimeline.domain.TimeLine;
import msa.timeline.msatimeline.repository.CommentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void writeComment(Comment comment) {
        comment.setUpdateTime(LocalDateTime.now());
        TimeLine timeLine = new TimeLine();
        timeLine.setTimeId(comment.getTempTimeId());
        comment.setTimeId(timeLine);
        try {
            commentRepository.save(comment);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("전송된 데이터가 올바르지 않습니다.");
        } catch (InvalidDataAccessApiUsageException ex) {
            throw new InvalidDataAccessApiUsageException("null값을 저장할 수 없습니다.");
        }
    }

    @Override
    public void updateComment(Comment comment) {
        commentRepository.updateComment(comment);
    }

    @Override
    public void deleteCommentByCommentId(Long commentId) {
        commentRepository.deleteCommentByCommentId(commentId);
    }

    @Override
    public List<Comment> findCommentListByTimeId(TimeLine timeLine) {
        List<Comment> comments = new ArrayList<>();
        comments = commentRepository.findCommentByTimeIdOrderByUpdateTimeDesc(timeLine);
        return comments;
    }
}
