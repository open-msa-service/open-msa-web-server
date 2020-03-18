package com.msa.timeline.service.impl;

import com.msa.timeline.domain.Comment;
import com.msa.timeline.domain.TimeLine;
import com.msa.timeline.dtos.ResponseMessage;
import com.msa.timeline.repository.CommentRepository;
import com.msa.timeline.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    private ResponseMessage responseMessage;

    @Override
    public ResponseEntity<Object> writeCommentInTimeLine(Comment comment) {
        TimeLine timeLine = new TimeLine();
        timeLine.setTimeId(comment.getTempTimeId());
        comment.setTimeId(timeLine);
        try{
            commentRepository.save(comment);
        }catch(DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("", ex);
        }

        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "댓글을 성공적으로 작성되었습니다.", null);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


}
