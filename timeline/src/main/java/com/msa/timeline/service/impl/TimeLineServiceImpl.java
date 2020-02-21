package com.msa.timeline.service.impl;

import com.msa.timeline.domain.Comment;
import com.msa.timeline.domain.Like;
import com.msa.timeline.domain.TimeLine;
import com.msa.timeline.dtos.ResponseMessage;
import com.msa.timeline.repository.CommentRepository;
import com.msa.timeline.repository.LikeRepository;
import com.msa.timeline.repository.TimeLineRepository;
import com.msa.timeline.service.TimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TimeLineServiceImpl implements TimeLineService {

    @Autowired private TimeLineRepository timeLineRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private LikeRepository likeRepository;

    private ResponseMessage responseMessage;

    @Override
    public ResponseEntity<Object> writeTimeLine(TimeLine timeLine) {
        try{
            timeLineRepository.save(timeLine);
        }catch(DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("", ex);
        }
        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "게시글이 성공적으로 작성되었습니다.", null);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> writeComments(Comment comment) {
        try{
            commentRepository.save(comment);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("", ex);
        }
        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "댓글이 성공적으로 작성되었습니다.", null);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> clickLikes(Like like) {
        try{
            likeRepository.save(like);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("", ex);
        }
        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "좋아요 변경이 성공적으로 되었습니다.", null);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
