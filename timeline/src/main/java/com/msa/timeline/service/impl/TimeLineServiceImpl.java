package com.msa.timeline.service.impl;

import com.msa.timeline.domain.Comment;
import com.msa.timeline.domain.Like;
import com.msa.timeline.domain.LikeType;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TimeLineServiceImpl implements TimeLineService {

    @Autowired private TimeLineRepository timeLineRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private LikeRepository likeRepository;

    private ResponseMessage responseMessage;

    @Override
    public ResponseEntity<Object> searchAllTimeLine() {
        List<TimeLine> timeLines = null;
        try{
            timeLines = timeLineRepository.findAll();
        }catch(DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("", ex);
        }
        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "게시글 목록을 가져오는데 성공했습니다.", null);
        responseMessage.setData(timeLines, "timeline");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> writeTimeLine(TimeLine timeLine) {
        try{
            StringBuilder fileLocation = new StringBuilder();
            for(String str : timeLine.getFileNameList()){
                fileLocation.append(str.replaceAll(",", " ")).append(",");
            }
            timeLine.setFileLocation(fileLocation.toString());
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

//    @Override
//    public ResponseEntity<Object> clickLikes(Like like) {
//        try{
//            if(LikeType.TIMELINE.equals(like.getLikeType())){
//                long count = likeRepository.countByUserIdAndTimeId(like.getUserId(), like.getTimeId());
//                if(count > 0){
//                    likeRepository.delete(like);
//                }else{
//                    likeRepository.save(like);
//                }
//            }else{
//                long count = likeRepository.countByUserIdAndCommentId(like.getUserId(),like.getCommentId());
//                if(count > 0){
//                    likeRepository.delete(like);
//                }else{
//                    likeRepository.save(like);
//                }
//            }
//        }catch (DataIntegrityViolationException ex){
//            throw new DataIntegrityViolationException("", ex);
//        }
//        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "좋아요 변경이 성공적으로 되었습니다.", null);
//        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
//    }


}
