package com.msa.timeline.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.timeline.domain.Comment;
import com.msa.timeline.domain.Like;
import com.msa.timeline.domain.LikeType;
import com.msa.timeline.domain.TimeLine;
import com.msa.timeline.dtos.ResponseMessage;
import com.msa.timeline.repository.CommentRepository;
import com.msa.timeline.repository.LikeRepository;
import com.msa.timeline.repository.TimeLineRepository;
import com.msa.timeline.service.FileUploadDownloadService;
import com.msa.timeline.service.TimeLineService;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TimeLineServiceImpl implements TimeLineService {

    private static final Logger logger = LoggerFactory.getLogger(TimeLineServiceImpl.class);

    @Autowired private TimeLineRepository timeLineRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private LikeRepository likeRepository;

    @Autowired
    private FileUploadDownloadService fileUploadDownloadService;

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
    public ResponseEntity<Object> writeTimeLine(MultipartFile[] file, String tempTimeline, boolean update) {

        TimeLine timeLine = convertStringToTimeLine(tempTimeline);

        try{
            if(timeLine.getIsUpdated()){
                setImageFileNames(timeLine);
                fileUploadDownloadService.storeFile(file);
            }

            if(update){
                timeLineRepository.updateTimeline(timeLine);
            }else{
                timeLineRepository.save(timeLine);
            }

        }catch(DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("", ex);
        }

        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "게시글이 성공적으로 작성되었습니다.", null);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    private void setImageFileNames(TimeLine timeLine) {
        StringBuilder fileLocation = new StringBuilder();
        if(!StringUtils.isEmpty(timeLine.getFileNameList())){
            for(String str : timeLine.getFileNameList()){
                fileLocation.append(str.replaceAll(",", " ")).append(",");
            }
            timeLine.setFileLocation(fileLocation.toString());
        }
    }

    private TimeLine convertStringToTimeLine(String tempTimeline) {
        ObjectMapper objectMapper = new ObjectMapper();
        TimeLine timeLine = null;
        try {
            timeLine = objectMapper.readValue(tempTimeline, TimeLine.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return timeLine;
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
    public ResponseEntity<Object> searchAllTimeLineByUserId(String userId) {
        List<TimeLine> timeLines = null;
        try{
            timeLines = timeLineRepository.findByUserIdOrderByUpdateTimeDesc(userId);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("", ex);
        }
        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "게시글 목록을 가져오는데 성공했습니다.", null);
        responseMessage.setData(timeLines, "timeline");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteTimeLineById(Long timeId) {

        try{
            timeLineRepository.deleteById(timeId);
        }catch (EmptyResultDataAccessException e){
            throw new EmptyResultDataAccessException("",0);
        }

        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "게시글을 삭제하는데 성공했습니다.", null);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


}
