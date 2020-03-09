package com.msa.timeline.service.impl;

import com.msa.timeline.domain.Like;
import com.msa.timeline.domain.LikeType;
import com.msa.timeline.domain.TimeLine;
import com.msa.timeline.dtos.ResponseMessage;
import com.msa.timeline.repository.LikeRepository;
import com.msa.timeline.service.LikeService;
import com.msa.timeline.service.TimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private TimeLineService timeLineService;

    private ResponseMessage responseMessage;

    @Override
    @Transactional
    public ResponseEntity<Object> clickLikeService(Like like) {
        String userId = like.getUserId();
        long timeId = like.getTempTimeId();


        TimeLine timeLine = new TimeLine();
        timeLine.setTimeId(timeId);
        timeLine.setUserId(userId);

        Like likes = new Like(userId, timeLine, LikeType.TIMELINE);
        int counts = likeRepository.countLikeByTimeIdAndUserId(timeLine, userId);
        try{
            if(counts != 0){
                likeRepository.deleteLikeByTimeIdAndUserId(timeLine, userId);
            }else{
                likeRepository.save(likes);
            }
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("좋아요 클릭에 실패했습니다.");
        }catch (EntityNotFoundException ex){
            throw new EntityNotFoundException("");
        }

        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "좋아요를 눌렀습니다.", null);
        responseMessage.setData(likeRepository.findByTimeId(timeLine),"like");
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }

}
